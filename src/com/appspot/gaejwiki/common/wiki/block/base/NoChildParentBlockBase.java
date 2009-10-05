package com.appspot.gaejwiki.common.wiki.block.base;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;



/**
 * WikiObject
 * �e�ɂ��q�ɂ��Ȃ�Ȃ��n
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
		// �Ȃɂ����Ȃ��B
	}

	@Override
	public WikiObjectBlockI getParent() {
		// �e�͑��݂��Ȃ�
		return null;
	}

	@Override
	public void setParent(WikiObjectBlockI wikiobject) {
		// �Ȃɂ����Ȃ��B
	}
	
	protected String getData() {
		return data;
	}

	@Override
	public String toDebugString() {
		return data + "\n";
	}

}
