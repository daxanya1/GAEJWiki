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
package com.appspot.gaejwiki.common.template;

public class TemplateLoader {

	/**
	 * @param string
	 * @return
	 */
	public TemplateData loadTemplate(String string) {
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
		return null;
	}

}
