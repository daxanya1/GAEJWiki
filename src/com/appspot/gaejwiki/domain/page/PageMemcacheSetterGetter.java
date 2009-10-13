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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 *
 * @author Ryuichi Danno
 */
public class PageMemcacheSetterGetter {

	public static final String KEY_MEMCACHEHTML = "_HTML";
	public static final String KEY_MEMCACHEWIKI = "_WIKI";
	
	public PageData getPageData(Key keyname) {
		if (keyname == null) {
			return null;
		}
		
		MemcacheService memcache = getMemcacheService();
		String memhtmldata = (String)memcache.get(keyname + KEY_MEMCACHEHTML);
		String memwikidata = (String)memcache.get(keyname + KEY_MEMCACHEWIKI);
		if (memhtmldata == null || memwikidata == null) {
			return null;
		}
		
		PageData pagedata = new PageData();
		pagedata.setHtmlWiki(memhtmldata, memwikidata);
		return pagedata;
	}
	
	public void setPageData(Key keyname, PageData pagedata) {
		if (pagedata == null) {
			return;
		}
		setPageData(keyname, pagedata.get(PageData.HTMLDATAKEY), pagedata.get(PageData.WIKIDATAKEY));
	}
	
	public void setPageData(Key keyname, String htmldata, String wikidata) {
		if (keyname == null || htmldata == null || wikidata == null) {
			return;
		}
		
		MemcacheService memcache = getMemcacheService();
		memcache.put(keyname + KEY_MEMCACHEHTML, htmldata);
		memcache.put(keyname + KEY_MEMCACHEWIKI, wikidata);
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
	public void clearHtmlData(Key keyname) {
		if (keyname == null) {
			return;
		}
		
		MemcacheService memcache = getMemcacheService();
		memcache.put(keyname + KEY_MEMCACHEHTML, null);
	}
}
