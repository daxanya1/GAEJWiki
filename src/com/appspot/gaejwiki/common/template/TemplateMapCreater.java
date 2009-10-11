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

import com.appspot.gaejwiki.domain.page.PageData;
import com.appspot.gaejwiki.domain.page.PageParam;

public class TemplateMapCreater {

	public TemplateReplaceMap createMenuBodyMap(PageParam bodyparam, PageData bodypage, PageData menupage) {
		if (bodyparam == null || bodypage == null) {
			return null;
		}
		
		TemplateReplaceMap templateparam = new TemplateReplaceMap();
		templateparam.put(TemplateReplaceMap.KEY_BODY, bodypage.get(PageData.HTMLDATAKEY));
		templateparam.put(TemplateReplaceMap.KEY_TITLE, bodyparam.get(PageParam.PAGEKEY));
		if (menupage != null) {
			templateparam.put(TemplateReplaceMap.KEY_MENU, menupage.get(PageData.HTMLDATAKEY));
		}
		
		return templateparam;
	}

	/**
	 * @param pageparam
	 * @param bodypagedata
	 * @param object
	 * @return
	 */
	public TemplateReplaceMap createEditBodyMap(PageParam bodyparam, PageData bodypage) {
			if (bodyparam == null) {
				return null;
			}
			
			TemplateReplaceMap templateparam = new TemplateReplaceMap();
			templateparam.put(TemplateReplaceMap.KEY_TITLE, bodyparam.get(PageParam.PAGEKEY));
			
			if (bodypage != null) {
				templateparam.put(TemplateReplaceMap.KEY_BODYWIKI, bodypage.get(PageData.WIKIDATAKEY));
				templateparam.put(TemplateReplaceMap.KEY_BODY, bodypage.get(PageData.HTMLDATAKEY));
			}
			
			return templateparam;
	}

}
