package com.appspot.gaejwiki.common.wiki.inline.base;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public class YesChildNoParentInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private StringBuffer data = new StringBuffer();
	
	@Override
	public void addChildInline(WikiObjectInlineI wikiobject) {
		// ‚È‚É‚à‚µ‚È‚¢
	}

	@Override
	public void addString(String str) {
		data.append(str);
	}

	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public boolean isAddChildInline() {
		return false;
	}

	@Override
	public boolean isAddToParent() {
		return true;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}

}
