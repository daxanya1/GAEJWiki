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
package com.appspot.gaejwiki.common.wiki.inline.base;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

public abstract class ChildOnlyInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	private String indata = null;
	private String accesspagename = null;
	
	@Override
	public void set(String str, WikiInlineParser parser) {
		rawdata = str;
		indata = getMatchData(rawdata);
		if (parser != null) {
			checkPage(parser);
			accesspagename = parser.getAccessPageName();
		}
	}
	
	abstract protected String getMatchData(String rawdata);

	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}

	@Override
	public String toDebugString() {
		return rawdata;
	}
	
	@Override
	public String toString() {
		return indata;
	}
	
	public String getAccessPageName() {
		return accesspagename;
	}
	
	/**
	 * 固有の正規表現のパターンを返す
	 * @return 正規表現文字列
	 */
	public abstract String getPattern();

	/**
	 * ページであれば、パーサに問い合わせてページが存在するかどうかを調べる
	 * @param parser
	 */
	protected abstract void checkPage(WikiInlineParser parser);

	
	static public class Sub {
		
		public String getExistHtmlString(String name, String accesspagename) {
			DomainParameter domainparam = DomainParameter.getDomainParameter();
			StringBuffer sb = new StringBuffer();
			sb.append("<a title=\"" + name + "\" href=\"" + domainparam.getViewURL(name) + "?ref=" + new TextUtils().encodeUrlString(accesspagename) + "\">" + name + "</a></span>");
			return sb.toString();
		}
		
		public String getNonExistHtmlString(String name, String accesspagename) {
			DomainParameter domainparam = DomainParameter.getDomainParameter();
			StringBuffer sb = new StringBuffer();
			sb.append("<span class=\"noexists\">");
			sb.append(name);
			sb.append("<a href=\"" + domainparam.getEditURL(name) + "?ref=" + new TextUtils().encodeUrlString(accesspagename) + "\">?</a></span>");
			return sb.toString();
		}
	}


}
