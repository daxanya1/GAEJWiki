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

import org.fb.xml.parser.BitXmlParser;

import com.appspot.gaejwiki.common.text.FileUtils;
import com.appspot.gaejwiki.common.xml.PtXmlParser;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

public class TemplateLoader {

	/**
	 * @param templatepath
	 * @return
	 */
	public TemplateData loadTemplate(String templatepath) {
		if (templatepath == null) {
			return null;
		}
		DomainParameter domainparam = DomainParameter.getDomainParameter();
		String templatebody = new Sub().loadTemplateFile(domainparam.getTemplateFilePath(templatepath));
		if (templatebody == null) {
			return null;
		}
        
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( templatebody );
		return parser.getTemplateData();
	}
	
	public static class Sub {
		
		public String loadTemplateFile(String filepath) {
			return new FileUtils().getFile(filepath);
		}
	}

}
