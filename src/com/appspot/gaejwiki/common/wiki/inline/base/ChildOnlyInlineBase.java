package com.appspot.gaejwiki.common.wiki.inline.base;

import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

public abstract class ChildOnlyInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	private String indata = null;
	
	@Override
	public void set(String str, WikiInlineParser parser) {
		rawdata = str;
		indata = new Util().matchSet(rawdata, getPattern());
		if (parser != null) {
			checkPage(parser);
		}
	}

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
		
		public String getExistHtmlString(String name) {
			return null;
		}
		
		public String getNonExistHtmlString(String name) {
			DomainParameter domainparam = DomainParameter.getDomainParameter();
			StringBuffer sb = new StringBuffer();
			sb.append("<span class=\"noexists\">");
			sb.append(name);
			sb.append("<a href=\"" + domainparam.get(DomainParameter.EDITURL) + "?page=" + name + "&amp;refer=ref\">?</a></span>");
			return sb.toString();
		}
	}


}
