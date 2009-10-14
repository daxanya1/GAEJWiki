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
package com.appspot.gaejwiki.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.gaejwiki.domain.page.PageParam;
import com.appspot.gaejwiki.domain.queue.QueueCommandI;
import com.appspot.gaejwiki.domain.queue.QueueCommandManager;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;

/**
 * Queueから呼び出される
 * URLにより処理を分ける
 * @author Ryuichi Danno
 */
@SuppressWarnings("serial")
public class QueueCommandServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(QueueCommandServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		new Sub().exec(req, resp);
	}
	
	static public class Sub {

		public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			// staticパラメータ初期化用に一度呼び出しておく
			DomainParameter domain = DomainParameter.getDomainParameter();
			
			PageParam pageparam = new ParamParser().parseUrl(req, domain.get(DomainParameter.QUEUECOMMANDURL));
			QueueCommandI queuecommand = new QueueCommandManager().getQueueCommand(pageparam);
			if (queuecommand == null) {
				logger.info("queue execcommand: notfound");
				return;
			}
			
			resp.setContentType("text/plain; charset=UTF-8");
			String retcode = queuecommand.exec(pageparam) ? "ok" : "ng";
			logger.info("queue command:" + queuecommand.getName() + ":" + retcode);
			resp.getWriter().print(retcode);
		}
	}
}
