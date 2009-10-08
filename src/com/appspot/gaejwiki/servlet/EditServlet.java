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
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.gaejwiki.domain.page.PageSaver;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;

/**
 *
 * @author Ryuichi Danno
 */
@SuppressWarnings("serial")
public class EditServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		
		// static�p�����[�^�������p�Ɉ�x�Ăяo���Ă���
		DomainParameter domain = DomainParameter.getDomainParameter();
		
		// �p�����[�^�𕪐�
		Map<String, String> bodyparam = new ParamParser().parseUrl(req, domain.get(DomainParameter.EDITURL));
		
		new PageSaver().savePage(bodyparam.get(ParamParser.PAGEKEY), bodyparam.get(ParamParser.REFERKEY));
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world edit");
	}
}
