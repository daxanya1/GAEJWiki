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

import java.util.logging.Logger;

import org.fb.xml.parser.BitXmlParser;

import com.appspot.gaejwiki.common.text.FileUtils;
import com.appspot.gaejwiki.common.xml.PtXmlParser;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

public class TemplateLoader {
	private static final Logger logger = Logger.getLogger(TemplateLoader.class.getName());

	/**
	 * @param templatepath
	 * @return
	 */
	public TemplateData loadTemplate(String templatepath) {
		if (templatepath == null) {
			logger.info("loadtemplate path null");
			return null;
		}
		DomainParameter domainparam = DomainParameter.getDomainParameter();
		String templatebody = loadTemplateFile(domainparam.getTemplateFilePath(templatepath));
		if (templatebody == null) {
			logger.info("loadtemplate templatebody null:" + domainparam.getTemplateFilePath(templatepath));
			return null;
		}
        
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( templatebody );
		return parser.getTemplateData();
	}

	protected String loadTemplateFile(String filepath) {
		logger.info("loadtemplate:" + filepath);
		return new FileUtils().getFile(filepath, false);
	}


}
