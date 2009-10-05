package com.appspot.gaejwiki.common.wiki.block.base;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;



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
public abstract class NoChildParentBlockBase implements WikiObjectBlockI {

	private String data = new String("");
	
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

}
