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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.gaejwiki.common.template.TemplateLoader;
import com.appspot.gaejwiki.common.template.TemplateMapCreater;
import com.appspot.gaejwiki.common.template.TemplateMixer;
import com.appspot.gaejwiki.domain.menu.MenuMaker;
import com.appspot.gaejwiki.domain.page.PageLoader;
import com.appspot.gaejwiki.domain.page.PageParam;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;

/**
 *
 * @author Ryuichi Danno
 */
@SuppressWarnings("serial")
public class ViewServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		new Sub().exec(req, resp);
	}
	
	static public class Sub {

		public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			// staticパラメータ初期化用に一度呼び出しておく
			DomainParameter domain = DomainParameter.getDomainParameter();
			
			// パラメータを分析
			PageParam pageparam = new ParamParser().parseUrl(req, domain.get(DomainParameter.VIEWURL));
			
			// ページをロード（カウンタを増やす）
			// ページをパースしてHTMLにする（Memcacheに入っていれば取り出す）
			// ページに値をマッピング（カウンタ等動的要素）を含む
			// デフォルトページで、かつない場合は、デフォルト要素を取り出す
			String bodypage = new PageLoader().loadPage(pageparam);
			
			// ページがなければリダイレクトで終わり
			if (bodypage == null) {
				// 含まれていなければ、デフォルトページへリダイレクトして終わり
				resp.sendRedirect(domain.getDefaultViewURL());
				return;
			}
			
			// メニューをロード（カウンタは増やさない）
			// メニューをパースしてHTMLにする（Memcacheに入っていれば取り出す）
			// なかったらデフォルトメニューを返す
			String menupage = new PageLoader().loadPage(new MenuMaker().getMenuParam());
			
			// テンプレート用マップ作成 
			// readテンプレートをロード
			// パラメータをマッピング
			String viewoutput = new TemplateMixer().makeHtml(
					new TemplateLoader().loadTemplate(domain.get(DomainParameter.VIEWTEMPLATE)), 
					new TemplateMapCreater().createMenuBodyMap(pageparam, bodypage, menupage));
			
			if (viewoutput == null) {
				// エラー画面を返す(最終的にはリダイレクト）
				viewoutput = "error";
			}
			
			// 出力
			resp.setContentType("text/html");
			resp.getWriter().print(viewoutput);
			
		}
		
	}
}
