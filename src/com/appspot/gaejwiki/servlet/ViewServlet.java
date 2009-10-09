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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.gaejwiki.common.template.TemplateLoader;
import com.appspot.gaejwiki.common.template.TemplateMapCreater;
import com.appspot.gaejwiki.common.template.TemplateMixer;
import com.appspot.gaejwiki.domain.menu.MenuMaker;
import com.appspot.gaejwiki.domain.page.PageLoader;
import com.appspot.gaejwiki.domain.page.PageParam;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;

/**
 *
 * @author Ryuichi Danno
 */
@SuppressWarnings("serial")
public class ViewServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		new Sub().exec(req, resp);
	}
	
	static public class Sub {

		public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			// static�p�����[�^�������p�Ɉ�x�Ăяo���Ă���
			DomainParameter domain = DomainParameter.getDomainParameter();
			
			// �p�����[�^�𕪐�
			PageParam pageparam = new ParamParser().parseUrl(req, domain.get(DomainParameter.VIEWURL));
			
			// �y�[�W�����[�h�i�J�E���^�𑝂₷�j
			// �y�[�W���p�[�X����HTML�ɂ���iMemcache�ɓ����Ă���Ύ��o���j
			// �y�[�W�ɒl���}�b�s���O�i�J�E���^�����I�v�f�j���܂�
			// �f�t�H���g�y�[�W�ŁA���Ȃ��ꍇ�́A�f�t�H���g�v�f�����o��
			String bodypage = new PageLoader().loadPage(pageparam);
			
			// �y�[�W���Ȃ���΃��_�C���N�g�ŏI���
			if (bodypage == null) {
				// �܂܂�Ă��Ȃ���΁A�f�t�H���g�y�[�W�փ��_�C���N�g���ďI���
				resp.sendRedirect(domain.getDefaultViewURL());
				return;
			}
			
			// ���j���[�����[�h�i�J�E���^�͑��₳�Ȃ��j
			// ���j���[���p�[�X����HTML�ɂ���iMemcache�ɓ����Ă���Ύ��o���j
			// �Ȃ�������f�t�H���g���j���[��Ԃ�
			String menupage = new PageLoader().loadPage(new MenuMaker().getMenuParam());
			
			// �e���v���[�g�p�}�b�v�쐬 
			// read�e���v���[�g�����[�h
			// �p�����[�^���}�b�s���O
			String viewoutput = new TemplateMixer().makeHtml(
					new TemplateLoader().loadTemplate(domain.get(DomainParameter.VIEWTEMPLATE)), 
					new TemplateMapCreater().createMenuBodyMap(pageparam, bodypage, menupage));
			
			if (viewoutput == null) {
				// �G���[��ʂ�Ԃ�(�ŏI�I�ɂ̓��_�C���N�g�j
				viewoutput = "error";
			}
			
			// �o��
			resp.setContentType("text/html");
			resp.getWriter().print(viewoutput);
			
		}
		
	}
}
