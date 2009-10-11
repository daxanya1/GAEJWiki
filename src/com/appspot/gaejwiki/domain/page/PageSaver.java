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

import java.util.Calendar;

import com.appspot.gaejwiki.common.wiki.WikiParser;
import com.appspot.gaejwiki.data.dao.WikiData;
import com.appspot.gaejwiki.data.dao.WikiInfo;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

/**
 *
 * @author Ryuichi Danno
 */
public class PageSaver {

	public void savePage(String pagename, String wikidata) {
		if (pagename == null || wikidata == null) {
			return;
		}
		
		Sub sub = new Sub();
		
		WikiParser wikiparser = new WikiParser();
		String htmldata = wikiparser.parse(wikidata);
		
		WikiInfo info = sub.saveWikiInfo(pagename);
		sub.saveWikiData(info, htmldata, wikidata);
	}
	
	static public class Sub {

		/**
		 * WikiInfoがあるかないかで処理を分ける
		 * @param pagename
		 */
		public WikiInfo saveWikiInfo(String pagename) {
			assert(pagename != null);
			
	    	WikiInfo.Util util = new WikiInfo.Util();
	    	Key key = util.makeKey(pagename);
	    	WikiInfo wikiinfo = util.loadData(key);
	    	if (wikiinfo == null) {
	    		return createWikiInfo(pagename);
	    	} else {
	    		return updateWikiInfo(pagename, wikiinfo);
	    	}
	    	
		}
		
		/**
		 * Version情報をインクリメントして上書きする
		 * @param pagename
		 * @param wikiinfo
		 * @return
		 */
		public WikiInfo updateWikiInfo(String pagename, WikiInfo info) {
			assert(info != null);
			
	    	WikiInfo.Util util = new WikiInfo.Util();
	    	info.setVersion(info.getVersion() + 1);
	    	Calendar cal = Calendar.getInstance();
	    	info.setUpdatedate(cal.getTime());
	    	util.saveData(info);
	    	return info;
		}
		
		/**
		 * 
		 * @param pagename
		 * @return
		 */
		public WikiInfo createWikiInfo(String pagename) {
			assert(pagename != null);
			
			WikiInfo info = new WikiInfo();
			info.setPagename(pagename);
			info.setTodaycounter(1);
			info.setYesterdaycounter(0);
			info.setTotalcounter(1);
			info.setVersion(1);
	    	Calendar cal = Calendar.getInstance();
	    	info.setUpdatedate(cal.getTime());
	    	info.setCounterupdatedate(cal.getTime());
	    	WikiInfo.Util util = new WikiInfo.Util();
	    	info.setKey(util.makeKey(pagename));
	    	util.saveData(info);
	    	return info;
			
		}
		
		/**
		 * @param pagename
		 * @param wikidata
		 */
		public WikiData saveWikiData(WikiInfo info, String htmldata, String wikidata) {
			WikiData data = new WikiData();
	    	WikiData.Util util = new WikiData.Util();
	    	Calendar cal = Calendar.getInstance();
	    	data.setUpdatedate(cal.getTime());
	    	Key datakey = util.makeKey(info.getKey(), info.getVersion());
	    	data.setKey(datakey);
	    	byte[] partdata = wikidata.getBytes();
	    	data.setWikidata(new Blob(partdata));
	    	byte[] parthtmldata = htmldata.getBytes();
	    	data.setHtmldata(new Blob(parthtmldata));
	    	util.saveData(data);
	    	
	    	PageData pagedata = new PageData();
	    	pagedata.setHtmlWiki(htmldata, wikidata);
	    	new PageMemcacheSetterGetter().setPageData(datakey, pagedata);
	    	
	    	return data;
		}
	}

}
