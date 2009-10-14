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
import java.util.List;
import java.util.Set;

import com.appspot.gaejwiki.data.common.StringInsertDataExec;
import com.appspot.gaejwiki.data.dao.WikiRef;
import com.appspot.gaejwiki.domain.page.PageParam;

/**
 *
 * @author Ryuichi Danno
 */
public class AddRefQueueCommand implements QueueCommandI {

	static public final String COMMAND_NAME = COMMAND_ADDREF;
	
	@Override
	public boolean exec(PageParam pageparam) {
		if(pageparam == null) {
			return false;
		}
		
		String targetpagename = pageparam.get(PageParam.TARGETKEY);
		String originalpagename = pageparam.get(PageParam.REFERKEY);
		return new Sub().addPageRef(targetpagename, originalpagename);
	}

	@Override
	public String getName() {
		return COMMAND_NAME;
	}

	
	static public class Sub {
		public boolean addPageRef(String targetpagename, String originalpagename) {
			if (targetpagename == null || originalpagename == null) {
				return false;
			}
			
			StringInsertDataExec dataexec = new StringInsertDataExec();
			dataexec.setData(originalpagename);
			// 失敗の場合、３回再トライ
			if (!exec(targetpagename, dataexec)) {
				for (int i=0; i<3; i++) {
					// スリープさせたほうがよい？
					if (exec(targetpagename, dataexec)) {
						break;
					}
				}
			}
			
			return true;
		}
		
		public boolean exec(String targetpagename, StringInsertDataExec dataexec) {
			WikiRef.Util refutil = new WikiRef.Util();
			return refutil.execTransaction(refutil.makeKey(targetpagename, WikiRef.Util.KEYFOOTER_INCOMINGLINK), dataexec);
		}
	}
	
	static public class Util {
		/**
		 * それぞれのページでリファレンスを設定するために、queueにコマンドを送る
		 * @param info
		 * @param pageList
		 */
		public void queueAddRef(String originalpagename, List<String> pagelist) {
			assert(originalpagename != null);
			assert(pagelist != null);

	    	
	    	Set<String> set = new HashSet<String>();
	    	for (String targetpagename : pagelist) {
	    		if (targetpagename.length() == 0) {
	    			continue;
	    		}
	    		// 自分自身には送らない
	    		if (originalpagename.equals(targetpagename)) {
	    			continue;
	    		}
	    		// 重複を排除
	    		set.add(targetpagename);
	    	}
	    	
	    	QueueSetter queue = new QueueSetter();
	    	for (String targetpagename : set) {
	    		queue.setQueue(targetpagename, originalpagename, COMMAND_ADDREF);
	    	}
		}
	}

}
