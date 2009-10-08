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

/**
 *
 * @author Ryuichi Danno
 */
@SuppressWarnings("serial")
public class EditServlet extends HttpServlet {
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
		resp.getWriter().println("Hello, world edit");
	}
}
