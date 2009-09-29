package com.appspot.gaejwiki.common.wiki.inline.base;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public class NoChildParentInlineBase implements WikiObjectInlineI {

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
		return null;
	}

	@Override
	public boolean isAddChildInline() {
		return false;
	}

	@Override
	public boolean isAddToParent() {
		return false;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		// ‚È‚É‚à‚µ‚È‚¢
	}

}
