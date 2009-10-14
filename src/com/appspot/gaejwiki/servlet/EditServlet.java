/**
 Copyright 2009 GAEJWiki Team.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.appspot.gaejwiki.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.gaejwiki.common.template.TemplateLoader;
import com.appspot.gaejwiki.common.template.TemplateMapCreater;
import com.appspot.gaejwiki.common.template.TemplateMerger;
import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.data.common.StringInsertDataExec;
import com.appspot.gaejwiki.data.dao.WikiRef;
import com.appspot.gaejwiki.domain.page.PageData;
import com.appspot.gaejwiki.domain.page.PageLoader;
import com.appspot.gaejwiki.domain.page.PageParam;
import com.appspot.gaejwiki.domain.page.PageSaver;
import com.appspot.gaejwiki.domain.queue.ClearMemcachedQueueCommand;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;
import com.google.appengine.api.datastore.Key;

/**
 *
 * @author Ryuichi Danno
 */
@SuppressWarnings("serial")
public class EditServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(ViewServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		new Sub().exec(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		new Sub().exec(req, resp);
	}
	
	static public class Sub {

		public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			// staticパラメータ初期化用に一度呼び出しておく
			DomainParameter domain = DomainParameter.getDomainParameter();
			
			PageParam pageparam = new ParamParser().parseUrl(req, domain.get(DomainParameter.EDITURL));
			
			if (isCommit(pageparam)) {
				pageparam.put(PageParam.PAGEKEY, pageparam.get("pagetitle"));
				commitAndRedirect(resp, pageparam);
			} else {
				// パラメータを分析
				outputEditView(resp, pageparam);
			}
		}
		
		/**
		 * 
		 * @param pageparam
		 * @return
		 */
		public boolean isCommit(PageParam pageparam) {
			String write = pageparam.get("write");
			return (write != null);
		}
		
		/**
		 * @param req
		 * @param resp
		 * @param pageparam
		 */
		public void commitAndRedirect(HttpServletResponse resp, PageParam pageparam) throws IOException {
			assert(resp != null);
			assert(pageparam != null);
			
			// オリジナルと今回の変更点を比べて、変更されていなければ何もしない。
			//wikidata, wikidataoriginalは改行コードが、\nになっていない可能性があるので、置換する
			String wikidataoriginal = new TextUtils().removeReturnChar(pageparam.get("original"));
			String wikidata = new TextUtils().removeReturnChar(pageparam.get("wiki"));
			String pagename = pageparam.get(PageParam.PAGEKEY);
			
			if (wikidataoriginal == null || !wikidataoriginal.equals(wikidata)) {
				// 書き込みする
				boolean newflag = new PageSaver().savePage(pagename, wikidata);
				if (newflag) {
					logger.info("editcommit new-save:" + pagename);
					// 被リンク情報を取り出して、被リンク先のMemcached-HTMLをクリアするようQueueにセットする
					WikiRef.Util refutil = new WikiRef.Util();
					Key refkey = refutil.makeKey(pagename, WikiRef.Util.KEYFOOTER_INCOMINGLINK);
					new ClearMemcachedQueueCommand.Util().queueClearMemcached(pagename, refutil.getRefStringArray(refkey));
					
					// 披リンク情報を消す
					refutil.removeData(refkey);
					
					// ページ名を書き込む
					StringInsertDataExec execdata = new StringInsertDataExec();
					execdata.setData(pagename);
					refutil.execTransaction(WikiRef.Util.ALLPAGEKEY, execdata);
				} else {
					logger.info("editcommit save:" + pagename);
				}
			} else {
				// 書き込みしない
				logger.info("editcommit original same:" + pagename);
			}
			
			DomainParameter domain = DomainParameter.getDomainParameter();
			resp.sendRedirect(domain.getViewURL(pagename));
			logger.info("editcommit redirectpage:" + pagename);
			return;
		}

		/**
		 * 
		 * @param req
		 * @param resp
		 * @param pageparam
		 */
		public void outputEditView(HttpServletResponse resp, PageParam pageparam) throws IOException {
			assert(resp != null);
			assert(pageparam != null);
			
			// ページをロード（カウンタを増やさない）
			// ページをパースしてHTMLにする（Memcacheに入っていれば取り出す）
			// ページに値をマッピング（カウンタ等動的要素）を含む
			// デフォルトページで、かつない場合は、デフォルト要素を取り出す
			PageData bodypagedata = new PageLoader().loadPage(pageparam, false);
			DomainParameter domain = DomainParameter.getDomainParameter();
			
			// ページがない場合、デフォルトページへリダイレクト
			String pagename = pageparam.get(PageParam.PAGEKEY);
			if (bodypagedata == null && pagename == null) {
				resp.sendRedirect(domain.getDefaultViewURL());
				logger.info("sendredirect defaultpage");
				return;
			}
			
			// テンプレート用マップ作成 
			// readテンプレートをロード
			// パラメータをマッピング
			String editoutput = new TemplateMerger().makeHtml(
					new TemplateLoader().loadTemplate(domain.get(DomainParameter.EDITTEMPLATE)), 
					new TemplateMapCreater().createEditBodyMap(pageparam, bodypagedata));
			
			if (editoutput == null) {
				// エラー画面を返す(最終的にはリダイレクト）
				editoutput = "error";
			}
			
			// 出力
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().print(editoutput);
		}
			
	}
}
