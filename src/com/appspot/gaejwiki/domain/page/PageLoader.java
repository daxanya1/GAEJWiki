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

import com.appspot.gaejwiki.data.dao.WikiData;
import com.appspot.gaejwiki.data.dao.WikiInfo;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 *
 * @author Ryuichi Danno
 */
public class PageLoader {

	/**
	 * bodyparamのPAGEキーのValueからページ名を取り出して、ページを取得し、動的な値をマッピングして返す
	 * ページの取得方法は、
	 * 1.カウンタレコードからデータを取り出す
	 * 2.カウンタの値をインクリメントして保存する
	 * 3.Memcachedをページ名をKeyにして確認する。
	 * 4.3で取得できなかった場合、データレコードから最新のdataを取り出して、Memcachedに格納
	 * 5.動的な値（主にカウンタ）をマッピングして返す
	 * 
	 * @param bodyparam URLパラメータのMap
	 * @return HTML文字列
	 */
	public String loadPage(Map<String, String> bodyparam) {
		if (bodyparam == null) {
			return null;
		}
		String pagename = bodyparam.get(ParamParser.PAGEKEY);
		if (pagename == null) {
			return null;
		}
		
		Sub sub = new Sub();
		WikiInfo info = sub.getWikiInfo(pagename);
		String htmldata = sub.getHtmlData(info);
		Map<String, String> countermap = sub.createCounterMap(info);
		return new HtmlCounterMarger().margeHtml(htmldata, countermap);
	}
	
	static public class Sub {
		
		public String getHtmlData(WikiInfo info) {
			assert(info != null);
			WikiData.Util datautil = new WikiData.Util();
			Key datakey = datautil.makeKey(info.getKey(), info.getVersion());
			
			MemcacheService memcache = getMemcacheService();
			String memhtmldata = (String)memcache.get(datakey.toString());
			if (memhtmldata != null) {
				return memhtmldata;
			}
			
			WikiData data = datautil.loadData(datakey, false, true);
			if (data == null) {
				return null;
			}
			String htmldata = data.getHtmldata().toString();
			memcache.put(datakey.toString(), htmldata);
			return htmldata;
		}
		
		public MemcacheService getMemcacheService() {
			return MemcacheServiceFactory.getMemcacheService();
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
		 * @param pagename
		 * @return
		 */
		public WikiInfo getWikiInfo(String pagename) {
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
