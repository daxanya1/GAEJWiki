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
package com.appspot.gaejwiki.common.xml;

import java.util.Stack;
import java.util.logging.Logger;

import org.fb.xml.parser.BitXmlParserI;

import com.appspot.gaejwiki.common.template.TemplateData;


/**
 *
 * @author Ryuichi Danno
 */
public class PtXmlParser  implements BitXmlParserI {

	private static final Logger logger = Logger.getLogger(PtXmlParser.class.getName());

	private final TemplateData templatedata = new TemplateData();
	private final Stack<String> nowstack = new Stack<String>();
	
	private PtParam nowparam = null;
	
	public final String TAG_PHTML = "phtml";
	public final String TAG_PARAM = "param";
	public final String ATTRIVUTE_KEY = "key";
	public final String ATTRIVUTE_NAME = "name";
	public final String PARAMVALUE = "_";
	
	public final TemplateData getTemplateData() {
		return templatedata;
	}
	
	@Override
	public void endTag(String arg0) {
		if (arg0 == null) {
			return;
		}
		// stackの最初を見て、同じであればそのまま抜く
		// 違っていれば、同じものを探して抜く
		String first = null;
		while (!arg0.equals(first)) {
			if (nowstack.size() == 0) {
				break;
			}
			first = nowstack.pop();
		}
	}

	@Override
	public void property(String arg0, String arg1) {
		if (nowparam == null) {
			logger.warning("property: nowparam null");
			return;
		}
		
		if (arg0 == null || arg0.length() == 0 || "/".equals(arg0)) {
			return;
		}
		
		String nowtag = nowstack.lastElement();
		
		if (TAG_PARAM.equals(nowtag)) {
			if (ATTRIVUTE_KEY.equals(arg0)) {
				String param = new Util().trimDoubleQuote(arg1);
				nowparam.setMapKey(param, PARAMVALUE);
			}
		} else if (TAG_PHTML.equals(nowtag)) {
			if (ATTRIVUTE_NAME.equals(arg0)) {
				String param = new Util().trimDoubleQuote(arg1);
				nowparam.setName(param);
			}
		}
	}

	@Override
	public void startTag(String arg0) {
		nowstack.push(arg0);
		if (TAG_PHTML.equals(arg0)) {
			nowparam = new PtParam();
			templatedata.add(nowparam);
		}
	}

	@Override
	public void text(String arg0) {
		// 無視する
	} 
	
	static public class Util {
		
		public String trimDoubleQuote(String param) {
			if (param != null && param.length() > 0) {
				if (param.charAt(0) == '"' && param.charAt(param.length()-1) == '"') {
					param = param.substring(1, param.length()-1);
				}
			}
			return param;
		}
	}

}
