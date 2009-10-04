package com.appspot.gaejwiki.common.wiki.inline.base;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;
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
	public void set(String str, WikiObjectInlineFactory factory) {
		rawdata = str;
		// Ä‹Aˆ—‚ğs‚¤
		String line = new Util().matchSet(rawdata, getPattern());
		if (line != null) {
			childlist = new WikiInlineParser().parseInline(factory, line);
		}
	}

	public List<WikiObjectInlineI> getChildList() {
		return childlist;
	}

	public abstract String getPattern();

}
