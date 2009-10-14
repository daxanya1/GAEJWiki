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
package com.appspot.gaejwiki.domain.page;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.appspot.gaejwiki.common.text.FileUtils;
import com.appspot.gaejwiki.common.wiki.WikiParser;
import com.appspot.gaejwiki.data.dao.WikiData;
import com.appspot.gaejwiki.data.dao.WikiInfo;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.google.appengine.api.datastore.Key;

/**
 *
 * @author Ryuichi Danno
 */
public class PageLoader {
	private static final Logger logger = Logger.getLogger(PageLoader.class.getName());

	/**
	 * bodyparamのPAGEキーのValueからページ名を取り出して、ページを取得し、動的な値をマッピングして返す
	 * ページの取得方法は、
	 * 1.カウンタレコードからデータを取り出す
	 * 2.カウンタの値をインクリメントして保存する(incrementcounterがtrueの場合)
	 * 3.Memcachedをページ名をKeyにして確認する。
	 * 4.3で取得できなかった場合、データレコードから最新のdataを取り出して、Memcachedに格納
	 * 5.動的な値（主にカウンタ）をマッピングして返す
	 * 
	 * 特例として、デフォルトページかどうか確認して、デフォルトページかつデータがない場合、デフォルト情報を返す
	 * 
	 * @param pageparam Pageパラメータ
	 * @param incrementcounter カウンタをインクリメントする場合はtrue
	 * @return HTML文字列
	 */
	public PageData loadPage(PageParam pageparam, boolean incrementcounter) {
		if (pageparam == null) {
			logger.info("loadpage:pageparam null");
			return null;
		}
		String pagename = pageparam.get(PageParam.PAGEKEY);
		if (pagename == null) {
			logger.info("loadpage:pagename null");
			return null;
		}
		
		Sub sub = new Sub();
		WikiInfo info = sub.getWikiInfo(pagename, incrementcounter);
		if (info == null) {
			logger.info("loadpage:info null:" + pagename);
			return sub.getDefaultHtmlData(pagename);
		}
		
		PageData pagedata = sub.getHtmlData(info, getWikiParser());
		if (pagedata == null) {
			logger.info("loadpage:htmldata null:" + pagename);
			return null;
		}
		
		Map<String, String> countermap = sub.createCounterMap(info);
		String htmldata = new HtmlCounterMarger().margeHtml(pagedata.get(PageData.HTMLDATAKEY), countermap);
		pagedata.put(PageData.HTMLDATAKEY, htmldata);
		
		logger.info("loadpage:done:" + pagename);
		return pagedata;
	}
	
	protected WikiParser getWikiParser() {
		return new WikiParser();
	}
	

	/**
	 * ページが存在しているかどうか調べる
	 * @param pagename ページ名
	 * @return ページが存在していればtrue
	 */
	public boolean existPage(String pagename) {
		return (new Sub().getWikiInfo(pagename, false) != null);
	}
	
	static public class Sub {
		
		/**
		 * 
		 * @param info
		 * @return
		 */
		public PageData getHtmlData(WikiInfo info, WikiParser wikiparser) {
			assert(info != null);
			
			WikiData.Util datautil = new WikiData.Util();
			Key datakey = datautil.makeKey(info.getKey(), info.getVersion());
			
			PageData mempegedata = new PageMemcacheSetterGetter().getPageData(info.getPagename());
			if (mempegedata != null) {
				return mempegedata;
			}
			
			WikiData data = datautil.loadData(datakey, true);
			if (data == null) {
				return null;
			}
			
			String htmldata = wikiparser.parse(info.getPagename(), data.getWikidataString());
			PageData pegedata = new PageData();
			pegedata.setWikiHtml(data.getWikidataString(), htmldata);
			return pegedata;
		}

		
		/**
		 * 
		 * @param pagename
		 * @return
		 */
		public PageData getDefaultHtmlData(String pagename) {
			assert(pagename != null);
			
			DomainParameter domainparam = DomainParameter.getDomainParameter();
			if (pagename.equals(domainparam.get(DomainParameter.DEFAULTPAGENAME))) {
				logger.info("loadpage:defaultpage:" + DomainParameter.DEFAULTPAGENAME);
				String html = loadTemplateFile(domainparam.getTemplateFilePath(domainparam.get(DomainParameter.DEFAULTMESSAGE)));
				PageData data = new PageData();
				data.setWikiHtml(null, html);
				return data;
			} else {
				return null;
			}
		}
		
		public String loadTemplateFile(String filepath) {
			return new FileUtils().getFile(filepath, true);
		}


		/**
		 * @param info
		 * @return
		 */
		public Map<String, String> createCounterMap(WikiInfo info) {
			assert(info != null);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put(HtmlCounterMarger.TODAY, info.getTodaycounter().toString());
			map.put(HtmlCounterMarger.YESTERDAY, info.getYesterdaycounter().toString());
			map.put(HtmlCounterMarger.TOTAL, info.getTotalcounter().toString());
			
			return map;
		}
		
		/**
		 * 
		 * @param pagename ページ名
		 * @param incrementcounter trueの場合カウンタをインクリメントする
		 * @return
		 */
		public WikiInfo getWikiInfo(String pagename, boolean incrementcounter) {
			assert(pagename != null);
			
			if (incrementcounter) {
				return getWikiInfoIncrementCounter(pagename);
			} else {
				WikiInfo.Util infoutil = new WikiInfo.Util();
				return infoutil.loadData(infoutil.makeKey(pagename));
			}
		}

		/**
		 * @param pagename ページ名
		 * @return
		 */
		public WikiInfo getWikiInfoIncrementCounter(String pagename) {
			assert(pagename != null);
			
			WikiInfo.Util infoutil = new WikiInfo.Util();
			WikiInfo info = infoutil.loadAndIncrementData(infoutil.makeKey(pagename));
			
			// 失敗の場合、３回再トライ
			if (info == null) {
				for (int i=0; i<3; i++) {
					// スリープさせたほうがよい？
					info = infoutil.loadAndIncrementData(infoutil.makeKey(pagename));
					if (info != null) {
						break;
					}
				}
			}
			
			return info;
		}

	}


}
