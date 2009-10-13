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

import java.util.logging.Logger;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.data.dao.WikiRef;
import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions.Method;


/**
 *
 * @author Ryuichi Danno
 */
public class PageQueueSetter {

	private static final Logger logger = Logger.getLogger(PageQueueSetter.class.getName());
	/**
	 * @param pagename
	 */
	public void setQueue(String pagename) {
		if (pagename == null) {
			logger.info("queue:pagename null");
			return;
		}
		
		// pagenameのrefをロードする
    	WikiRef.Util util = new WikiRef.Util();
    	WikiRef ref = util.loadData(util.makeKey(pagename));
    	if (ref == null) {
			logger.info("queue:ref null");
    		return;
    	}
    	
    	String[] refdata = util.parserRefData(ref.getRefdata());
    	for (String refname: refdata) {
    		// それぞれのpagenameについて、queueに詰め込む
    		Queue queue = QueueFactory.getQueue("background-processing");
    		if (queue == null) {
    			queue = QueueFactory.getDefaultQueue();
    		}
			logger.info("queue:set:" + refname);
            queue.add(com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url(
            		"/admin/rebuild/" + new TextUtils().encodeUrlString(refname)).method(Method.GET));
    	}
		
	}

}
