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

import java.util.logging.Logger;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.domain.page.PageParam;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions.Method;


/**
 *
 * @author Ryuichi Danno
 */
public class QueueSetter {

	private static final Logger logger = Logger.getLogger(QueueSetter.class.getName());

	private static final String QUEUE_NAME = "background-processing";
	
	/**
	 * @param pagename
	 */
	public void setQueue(String targetpagename, String originalpagename, String command) {
		if (targetpagename == null || command == null) {
			logger.info("queue:pagename or command null");
			return;
		}
		
		Queue queue = getQueue(QUEUE_NAME);
		logger.info("queue:set:command:" + command + ": page:" + targetpagename);
		queue.add(com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url(
				new Sub().getUrl(targetpagename, originalpagename, command)
				).method(Method.GET));
	}
	
	protected Queue getQueue(String queuename) {
		Queue queue = QueueFactory.getQueue(queuename);
		if (queue == null) {
			queue = QueueFactory.getDefaultQueue();
		}
		return queue;
	}
	
	public static class Sub {
		
		public String getUrl(String targetpagename, String originalpagename, String command) {
			assert(targetpagename != null);
			assert(command != null);
			
			StringBuffer sb = new StringBuffer();
			sb.append(DomainParameter.getDomainParameter().getQueueCommandURL(command));
			sb.append("?" + PageParam.TARGETKEY + "=");
			sb.append(new TextUtils().encodeUrlString(targetpagename));
			if (originalpagename != null) {
				sb.append("&" + PageParam.REFERKEY + "=");
				sb.append(new TextUtils().encodeUrlString(originalpagename));
			}
			return sb.toString();
		}
	}

}
