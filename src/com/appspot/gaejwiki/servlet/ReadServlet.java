package com.appspot.gaejwiki.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.gaejwiki.common.template.TemplateLoader;
import com.appspot.gaejwiki.common.template.TemplateMapCreater;
import com.appspot.gaejwiki.common.template.TemplateMapper;
import com.appspot.gaejwiki.domain.menu.MenuMaker;
import com.appspot.gaejwiki.domain.page.PageLoader;
import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;

@SuppressWarnings("serial")
public class ReadServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		new Sub().exec(req, resp);
	}
	
	static public class Sub {

		public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			// static�p�����[�^�������p�Ɉ�x�Ăяo���Ă���
			DomainParameter domain = DomainParameter.getDomainParameter();
			
			// �p�����[�^�𕪐�
			Map<String, String> bodyparam = new ParamParser().parseUrl(req, domain.get(DomainParameter.VIEWURL));
			
			// �y�[�W�����[�h�i�J�E���^�𑝂₷�j
			// �y�[�W���p�[�X����HTML�ɂ���iMemcache�ɓ����Ă���Ύ��o���j
			// �y�[�W�ɒl���}�b�s���O�i�J�E���^�����I�v�f�j���܂�
			// �f�t�H���g�y�[�W�ŁA���Ȃ��ꍇ�́A�f�t�H���g�v�f�����o��
			String bodypage = new PageLoader().loadPage(bodyparam);
			
			// �y�[�W���Ȃ���΃��_�C���N�g�ŏI���
			if (bodypage == null) {
				// �܂܂�Ă��Ȃ���΁A�f�t�H���g�y�[�W�փ��_�C���N�g���ďI���
				
				return;
			}
			
			// ���j���[�����[�h�i�J�E���^�͑��₳�Ȃ��j
			// ���j���[���p�[�X����HTML�ɂ���iMemcache�ɓ����Ă���Ύ��o���j
			// �Ȃ�������f�t�H���g���j���[��Ԃ�
			String menupage = new PageLoader().loadPage(new MenuMaker().getMenuParam());
			
			// �e���v���[�g�p�}�b�v�쐬 
			Map<String, String> templatemap = new TemplateMapCreater().createMenuBodyMap(bodyparam, bodypage, menupage);
			
			// read�e���v���[�g�����[�h
			String readtemplate = new TemplateLoader().loadTemplate(domain.get(DomainParameter.VIEWTEMPLATE));
			
			// �e���v���[�g�ɒl���}�b�s���O�i�o�̓f�[�^���쐬�j
			String outputdata = new TemplateMapper().mappingTemplate(readtemplate, templatemap);
			
			// �o��
			resp.setContentType("text/html");
			resp.getWriter().print(outputdata);
			
			/*
			String templatepath = new String("template/index.pt");
			StringBuffer sb = new StringBuffer();
			
	        try {
	            FileReader in = new FileReader(templatepath);
	            BufferedReader br = new BufferedReader(in);
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            br.close();
	            in.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        
			BitXmlParser xParser1 = new BitXmlParser();
			PtXmlParser parser = new PtXmlParser();
			xParser1.setListener( parser );
			xParser1.parseSax( sb.toString() );
			List<PtParam> list = parser.getList();
			
			for (PtParam str : list) {
				System.out.println(str);
			}
		        
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello, world read");
			*/
		}
		
	}
}
