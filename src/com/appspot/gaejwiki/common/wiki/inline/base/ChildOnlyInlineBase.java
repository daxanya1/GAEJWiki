package com.appspot.gaejwiki.common.wiki.inline.base;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public abstract class ChildOnlyInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	private String indata = null;
	
	@Override
	public void set(String str, WikiObjectInlineFactory factory) {
		rawdata = str;
		// �ċA�������s��
		indata = new Util().matchSet(rawdata, getPattern());
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
	
	public String getData() {
		return indata;
	}
	
	public abstract String getPattern();
}