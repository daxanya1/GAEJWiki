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
			// staticパラメータ初期化用に一度呼び出しておく
			DomainParameter domain = DomainParameter.getDomainParameter();
			
			// パラメータを分析
			Map<String, String> bodyparam = new ParamParser().parseUrl(req, domain.get(DomainParameter.VIEWURL));
			
			// ページをロード（カウンタを増やす）
			// ページをパースしてHTMLにする（Memcacheに入っていれば取り出す）
			// ページに値をマッピング（カウンタ等動的要素）を含む
			// デフォルトページで、かつない場合は、デフォルト要素を取り出す
			String bodypage = new PageLoader().loadPage(bodyparam);
			
			// ページがなければリダイレクトで終わり
			if (bodypage == null) {
				// 含まれていなければ、デフォルトページへリダイレクトして終わり
				
				return;
			}
			
			// メニューをロード（カウンタは増やさない）
			// メニューをパースしてHTMLにする（Memcacheに入っていれば取り出す）
			// なかったらデフォルトメニューを返す
			String menupage = new PageLoader().loadPage(new MenuMaker().getMenuParam());
			
			// テンプレート用マップ作成 
			Map<String, String> templatemap = new TemplateMapCreater().createMenuBodyMap(bodyparam, bodypage, menupage);
			
			// readテンプレートをロード
			String readtemplate = new TemplateLoader().loadTemplate(domain.get(DomainParameter.VIEWTEMPLATE));
			
			// テンプレートに値をマッピング（出力データを作成）
			String outputdata = new TemplateMapper().mappingTemplate(readtemplate, templatemap);
			
			// 出力
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
