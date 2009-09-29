package com.appspot.gaejwiki.common.wiki.inline.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public class YesChildParentInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private StringBuffer data = new StringBuffer();
	private List<WikiObjectInlineI> childlist = new ArrayList<WikiObjectInlineI>();
	
	@Override
	public void addChildInline(WikiObjectInlineI wikiobject) {
		childlist.add(wikiobject);
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
		return true;
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
