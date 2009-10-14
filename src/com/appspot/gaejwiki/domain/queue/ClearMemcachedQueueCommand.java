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

package com.appspot.gaejwiki.domain.queue;

import java.util.HashSet;
import java.util.Set;

import com.appspot.gaejwiki.domain.page.PageMemcacheSetterGetter;
import com.appspot.gaejwiki.domain.page.PageParam;

/**
 *
 * @author Ryuichi Danno
 */
public class ClearMemcachedQueueCommand implements QueueCommandI {

	static public final String COMMAND_NAME = COMMAND_CLEARMEMCACHED;
	
	@Override
	public boolean exec(PageParam pageparam) {
		if(pageparam == null) {
			return false;
		}
		
		String pagename = pageparam.get(PageParam.TARGETKEY);
		getPageMemcacheSetterGetter().clearHtmlData(pagename);
		return true;
	}

	@Override
	public String getName() {
		return COMMAND_NAME;
	}
	
	protected PageMemcacheSetterGetter getPageMemcacheSetterGetter() {
		return new PageMemcacheSetterGetter();
	}
	
	static public class Util {
		
		/**
		 * Memcachedをクリアするためにqueueにコマンドを送る
		 * @param pagename
		 */
		public void queueClearMemcached(String originalpagename, String[] strlist) {
			if (originalpagename == null || strlist == null) {
				return;
			}

	    	Set<String> set = new HashSet<String>();
	    	for (String targetpagename : strlist) {
	    		if (targetpagename.length() == 0) {
	    			continue;
	    		}
	    		if (originalpagename.equals(targetpagename)) {
	    			continue;
	    		}
	    		// 重複を排除
	    		set.add(targetpagename);
	    	}
	    	
	    	QueueSetter queue = new QueueSetter();
	    	for (String targetpagename : set) {
	    		queue.setQueue(targetpagename, null, COMMAND_CLEARMEMCACHED);
	    	}
		}
	}

}
