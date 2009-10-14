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
package com.appspot.gaejwiki.domain.setting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.appspot.gaejwiki.common.text.TextUtils;

/**
 * 設定ファイルやJavaのシステムプロバティから取得できるパラメータを集約する
 * @author daxanya
 *
 */
public class DomainParameter {
	
	// --
	// singlton
	
	private static final DomainParameter domainparam = new DomainParameter();
	public static DomainParameter getDomainParameter() {
		return domainparam;
	}
	// --
	
	public static final String DOMAINURL = "domain";
	public static final String VIEWURL = "viewurl";
	public static final String EDITURL = "editurl";
	public static final String QUEUECOMMANDURL = "queuecommandurl";
	public static final String VIEWTEMPLATE = "viewtemplate";
	public static final String EDITTEMPLATE = "edittemplate";
	public static final String TIMEZONE = "timezone";
	public static final String DEFAULTPAGENAME = "defaultpage";
	public static final String DEFAULTMESSAGE = "defaultmessage";
	public static final String TEMPLATEPATH = "templatepath";
	public static final String LINESEPARATOR = "line.separator";
	
	private static final String DEFAULTPAGENAME_NOTSET_DEFAULT = "Welcome";
	private static final String DEFAULTMESSAGE_DEFAULT_FILENAME = "defaultmes.p.html";
	
	private Map<String, String> parammap = new HashMap<String, String>();
	
	private DomainParameter() {
		init();
	}
	
	private void init() {
		Sub sub = new Sub();
		sub.putMap(DOMAINURL, parammap);
		sub.putMap(VIEWURL, parammap);
		sub.putMap(EDITURL, parammap);
		sub.putMap(QUEUECOMMANDURL, parammap);
		sub.putMap(VIEWTEMPLATE, parammap);
		sub.putMap(EDITTEMPLATE, parammap);
		sub.putMap(TIMEZONE, parammap);
		sub.putMap(TEMPLATEPATH, parammap);
		sub.putMap(DEFAULTMESSAGE, parammap);
		sub.putMap(DEFAULTPAGENAME, parammap);
		if (parammap.get(DEFAULTMESSAGE) == null) {
			parammap.put(DEFAULTMESSAGE, DEFAULTMESSAGE_DEFAULT_FILENAME);
		}
		if (parammap.get(DEFAULTPAGENAME) == null) {
			parammap.put(DEFAULTPAGENAME, DEFAULTPAGENAME_NOTSET_DEFAULT);
		}
		// parammap.put(LINESEPARATOR, System.getProperty(LINESEPARATOR));
		parammap.put(LINESEPARATOR, "\n");
	}
	
	public String get(String key) {
		return parammap.get(key);
	}
	
	/**
	 * JUnit専用のパラメータセッター
	 * @param key
	 * @param value
	 */
	public void putUnitTestOnly(String key, String value) {
		parammap.put(key, value);
	}
	
	public String getLineSeparator() {
		return get(LINESEPARATOR);
	}
	
	public String getDefaultViewURL() {
		return "/" + get(VIEWURL) + new TextUtils().encodeUrlString(get(DEFAULTPAGENAME));
	}
	
	public String getViewURL(String page) {
		return "/" + get(VIEWURL) + new TextUtils().encodeUrlString(page);
	}
	
	public String getEditURL(String page) {
		return "/" + get(EDITURL) + new TextUtils().encodeUrlString(page);
	}
	
	public String getQueueCommandURL(String command) {
		return "/" + get(QUEUECOMMANDURL) + command;
	}
	
	public String getTemplateFilePath(String templatefilename) {
		return get(TEMPLATEPATH) + templatefilename;
	}
	
	public static class Sub {
		
		public void putMap(String key, Map<String, String> map) {
			map.put(key, System.getProperty("com.appspot.gaejwiki." + key));
		}
	}


}
