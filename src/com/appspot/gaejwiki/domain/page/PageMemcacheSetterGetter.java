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

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 *
 * @author Ryuichi Danno
 */
public class PageMemcacheSetterGetter {

	public static final String KEY_MEMCACHEWIKI = "_WIKI";
	public static final String KEY_MEMCACHEHTML = "_HTML";
	
	public PageData getPageData(String pagename) {
		if (pagename == null) {
			return null;
		}
		
		String memwikidata = (String)getMemcacheService().get(pagename + KEY_MEMCACHEWIKI);
		String memhtmldata = (String)getMemcacheService().get(pagename + KEY_MEMCACHEHTML);
		if (memwikidata == null || memhtmldata == null) {
			return null;
		}
		
		PageData pagedata = new PageData();
		pagedata.setWikiHtml(memwikidata, memhtmldata);
		return pagedata;
	}
	
	public void setPageData(String pagename, PageData pagedata) {
		if (pagedata == null) {
			return;
		}
		setPageWikiHtmlData(pagename, pagedata.get(PageData.HTMLDATAKEY), pagedata.get(PageData.WIKIDATAKEY));
	}
	
	public void setPageWikiHtmlData(String pagename, String wikidata, String htmldata) {
		if (pagename == null) {
			return;
		}
		
		if (wikidata != null) {
			getMemcacheService().put(pagename + KEY_MEMCACHEWIKI, wikidata);
		}
		if (htmldata != null) {
			getMemcacheService().put(pagename + KEY_MEMCACHEHTML, htmldata);
		}
	}

	
	/**
	 * 
	 * @return
	 */
	protected MemcacheService getMemcacheService() {
		return MemcacheServiceFactory.getMemcacheService();
	}

	/**
	 * @param key
	 */
	public void clearHtmlData(String pagename) {
		if (pagename == null) {
			return;
		}
		
		getMemcacheService().put(pagename + KEY_MEMCACHEHTML, null);
	}
}
