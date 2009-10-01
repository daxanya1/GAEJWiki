package com.appspot.gaejwiki.common.wiki.inline.base;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public class ChildOnlyInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	
	@Override
	public void set(String str, WikiObjectInlineFactory factory) {
		rawdata = str;
		// Ä‹Aˆ—‚Í‚µ‚È‚¢
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
