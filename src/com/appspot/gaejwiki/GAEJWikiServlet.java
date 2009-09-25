package com.appspot.gaejwiki;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fb.xml.parser.BitXmlParser;

import com.appspot.gaejwiki.common.xml.PtParam;
import com.appspot.gaejwiki.common.xml.PtXmlParser;

@SuppressWarnings("serial")
public class GAEJWikiServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		
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
		resp.getWriter().println("Hello, world");
	}
}
