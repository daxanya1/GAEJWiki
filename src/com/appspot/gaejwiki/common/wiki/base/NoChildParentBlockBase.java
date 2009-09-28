package com.appspot.gaejwiki.common.wiki.base;


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
public class NoChildParentBlockBase implements WikiObjectI {

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
	public void addChildBlock(WikiObjectI wikiobject) {
		// なにもしない。
	}

	@Override
	public WikiObjectI getParent() {
		// 親は存在しない
		return null;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		// なにもしない。
	}
	
	protected String getData() {
		return data;
	}
}
