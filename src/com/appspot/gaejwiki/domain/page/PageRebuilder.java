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


/**
 *
 * @author Ryuichi Danno
 */
public class PageRebuilder {

	/**
	 * @param pagename
	 */
	public void rebuildPage(String pagename) {
		if (pagename == null) {
			return;
		}
		
		new Sub().rebuildWikiData(pagename);
	}
	
	static public class Sub {

		/**
		 * @param pagename
		 */
		public void rebuildWikiData(String pagename) {
			assert(pagename != null);
			
			WikiInfo.Util util = new WikiInfo.Util();
			WikiInfo info = util.loadData(util.makeKey(pagename));
			if (info == null) {
				assert(false);
				return;
			}
			
			WikiData.Util datautil = new WikiData.Util();
			WikiData data = datautil.loadData(datautil.makeKey(info.getKey(), info.getVersion()), true, false);
			if (data == null) {
				assert(false);
				return;
			}
			String wikidata = data.getWikidataString();
			String htmldata = new WikiParser().parse(pagename, wikidata);
			data.setHtmldataString(htmldata);
	    	data.setUpdatedateNow();
	    	datautil.saveData(data);
	    	
	    	// 
	    	new PageMemcacheSetterGetter().clearHtmlData(data.getKey());
		}
		
	}

}
