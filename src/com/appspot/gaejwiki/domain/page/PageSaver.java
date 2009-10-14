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

import com.appspot.gaejwiki.common.wiki.WikiParser;
import com.appspot.gaejwiki.data.dao.WikiData;
import com.appspot.gaejwiki.data.dao.WikiInfo;
import com.appspot.gaejwiki.domain.queue.AddRefQueueCommand;
import com.google.appengine.api.datastore.Key;

/**
 *
 * @author Ryuichi Danno
 */
public class PageSaver {

	/**
	 * Pageの書き込みをする
	 * @param pagename
	 * @param wikidata
	 * @return　新規であればtrue
	 */
	public boolean savePage(String pagename, String wikidata) {
		if (pagename == null || wikidata == null) {
			return false;
		}
		
		WikiParser parser = getParser();
		String htmldata = parser.parse(pagename, wikidata);
		
		Sub sub = new Sub();
		WikiInfo info = sub.loadWikiInfo(pagename);
		boolean retcode;
    	if (info == null) {
    		retcode = true;
    		info = sub.createWikiInfo(pagename);
    	} else {
    		retcode = false;
    		info = sub.updateWikiInfo(pagename, info);
    	}

		sub.saveWikiData(info, wikidata);
		getPageMemcacheSetterGetter().setPageWikiHtmlData(pagename, wikidata, htmldata);
		new AddRefQueueCommand.Util().queueAddRef(pagename, parser.getNonExistsPageSet());
		
		return retcode;
	}
	
	protected WikiParser getParser() {
		return new WikiParser();
	}
	
	protected PageMemcacheSetterGetter getPageMemcacheSetterGetter() {
		return new PageMemcacheSetterGetter();
	}
	
	static public class Sub {

		/**
		 * WikiInfoがあるかないかで処理を分ける
		 * @param pagename
		 */
		public WikiInfo loadWikiInfo(String pagename) {
			assert(pagename != null);
			
	    	WikiInfo.Util util = new WikiInfo.Util();
	    	Key key = util.makeKey(pagename);
	    	return util.loadData(key);
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
	    	info.setUpdatedateNow();
	    	util.saveData(info);
	    	return info;
		}
		
		/**
		 * 新規で作成する
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
	    	info.setUpdatedateNow();
	    	info.setCounterupdatedateNow();
	    	WikiInfo.Util util = new WikiInfo.Util();
	    	info.setKey(util.makeKey(pagename));
	    	util.saveData(info);
	    	return info;
			
		}
		
		/**
		 * @param pagename
		 * @param wikidata
		 */
		public WikiData saveWikiData(WikiInfo info, String wikidata) {
			WikiData data = new WikiData();
	    	WikiData.Util util = new WikiData.Util();
	    	data.setUpdatedateNow();
	    	Key datakey = util.makeKey(info.getKey(), info.getVersion());
	    	data.setKey(datakey);
	    	data.setWikidataString(wikidata);
	    	util.saveData(data);
	    	return data;
		}
	}



}
