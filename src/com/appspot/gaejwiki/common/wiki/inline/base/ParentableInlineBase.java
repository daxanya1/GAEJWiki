package com.appspot.gaejwiki.common.wiki.inline.base;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public abstract class ParentableInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	private List<WikiObjectInlineI> childlist = null;
	
	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}
	
	@Override
	public void set(String str, WikiInlineParser parser) {
		rawdata = str;
		// çƒãAèàóùÇçsÇ§
		String line = new Util().matchSet(rawdata, getPattern());
		if (line != null) {
			childlist = parser.parseInline(line);
		}
	}
	
	@Override
	public String toDebugString() {
		return new Util().toDebugString(getDebugStringHeader(), getChildList());
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (childlist != null) {
			for (WikiObjectInlineI inline : childlist) {
				sb.append(inline.toString());
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(toHtmlStringHeader());
		for (WikiObjectInlineI inline : getChildList()) {
			sb.append(inline.toHtmlString());
		}
		sb.append(toHtmlStringFooter());
		return sb.toString();
	}

	public List<WikiObjectInlineI> getChildList() {
		return childlist;
	}
	
	protected String getRawData() {
		return rawdata;
	}

	abstract public String getPattern();

	abstract public String getDebugStringHeader();
	
	abstract public String toHtmlStringHeader();
	
	abstract public String toHtmlStringFooter();
	
	
}
