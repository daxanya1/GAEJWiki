package com.appspot.gaejwiki.common.wiki.block.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;



/**
 * WikiObject
 * 親にも子にもなれない系
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public class NoChildParentBlockBase implements WikiObjectBlockI {

	private String data = new String("");
	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	
	@Override
	public void addLine(String str) {
		data = str;
	}
	
	@Override
	public boolean isAddChildBlock() {
		return false;
	}
	
	@Override
	public boolean isAddLine() {
		return false;
	}
	
	@Override
	public boolean isSameBlockAddLine() {
		return false;
	}
	
	@Override
	public boolean isAddToParent() {
		return false;
	}
	
	@Override
	public boolean isReset() {
		return true;
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	@Override
	public void addChildBlock(WikiObjectBlockI wikiobject) {
		// なにもしない。
	}

	@Override
	public WikiObjectBlockI getParent() {
		// 親は存在しない
		return null;
	}

	@Override
	public void setParent(WikiObjectBlockI wikiobject) {
		// なにもしない。
	}
	
	protected String getData() {
		return data;
	}

	@Override
	public String toDebugString() {
		return data + "\n";
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		// ここで頭部分のカットが必要
		inlinelist.addAll(parser.parseInline(data));

	}
}
