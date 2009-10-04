package com.appspot.gaejwiki.common.wiki.inline.base;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public abstract class OneCharacterInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	
	@Override
	public void set(String str, WikiObjectInlineFactory factory) {
		rawdata = str;
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

}
