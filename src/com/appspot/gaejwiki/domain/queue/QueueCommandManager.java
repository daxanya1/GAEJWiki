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



import java.util.HashMap;
import java.util.Map;

import com.appspot.gaejwiki.domain.page.PageParam;

/**
 *
 * @author Ryuichi Danno
 */
public class QueueCommandManager {

	static private final Map<String, QueueCommandI> commandmap = new HashMap<String, QueueCommandI>();
	
	static {
		commandmap.put(ClearMemcachedQueueCommand.COMMAND_NAME, new ClearMemcachedQueueCommand());
		commandmap.put(AddRefQueueCommand.COMMAND_NAME, new AddRefQueueCommand());
	}
	
	/**
	 * @param pageparam
	 * @return
	 */
	public QueueCommandI getQueueCommand(PageParam pageparam) {
		if (pageparam == null) {
			return null;
		}
		
		String command = pageparam.get(PageParam.PAGEKEY);
		if (command == null) {
			return null;
		}
		
		return commandmap.get(command);
	}

}
