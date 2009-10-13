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

import com.appspot.gaejwiki.common.template.TemplateLoader;
import com.appspot.gaejwiki.common.template.TemplateMapCreater;
import com.appspot.gaejwiki.common.template.TemplateMerger;
import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.domain.page.PageData;
import com.appspot.gaejwiki.domain.page.PageLoader;
import com.appspot.gaejwiki.domain.page.PageParam;
import com.appspot.gaejwiki.domain.page.PageQueueSetter;
import com.appspot.gaejwiki.domain.page.PageRebuilder;
import com.appspot.gaejwiki.domain.page.PageSaver;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;

/**
 *
 * @author Ryuichi Danno
 */
@SuppressWarnings("serial")
public class RebuildServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(ViewServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		new Sub().exec(req, resp);
	}
	
	static public class Sub {

		public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			PageParam pageparam = new ParamParser().parseUrl(req, "rebuild/");
			rebuild(resp, pageparam);
		}
		
		/**
		 * @param req
		 * @param resp
		 * @param pageparam
		 */
		public void rebuild(HttpServletResponse resp, PageParam pageparam) throws IOException {
			assert(resp != null);
			assert(pageparam != null);
			
			String pagename = pageparam.get(PageParam.PAGEKEY);
			// 書き込みする
			new PageRebuilder().rebuildPage(pagename);
			logger.info("rebild savepage:" + pagename);
			resp.setContentType("text/plain; charset=UTF-8");
			resp.getWriter().print("ok");
		}
	}
}
