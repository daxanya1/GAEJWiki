package com.appspot.gaejwiki.common.wiki.block.base;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;



/**
 * WikiObject
 * éqãüÇ…ÇÕÇ»ÇÍÇÈÇ™çsí«â¡Ç™Ç≈Ç´Ç»Ç¢
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public class YesChildNoAddlineBlockBase implements WikiObjectBlockI {

	private WikiObjectBlockI parent = null;
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
		return true;
	}
	
	@Override
	public boolean isReset() {
		return false;
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	@Override
	public void addChildBlock(WikiObjectBlockI wikiobject) {
		// Ç»Ç…Ç‡ÇµÇ»Ç¢ÅB
	}

	@Override
	public WikiObjectBlockI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectBlockI wikiobject) {
		parent = wikiobject;
	}

	protected String getData() {
		return data;
	}

	@Override
	public String toDebugString() {
		return data + "\n";
	}
}
