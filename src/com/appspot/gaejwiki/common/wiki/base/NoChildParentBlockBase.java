package com.appspot.gaejwiki.common.wiki.base;


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
		// �Ȃɂ����Ȃ��B
	}

	@Override
	public WikiObjectI getParent() {
		// �e�͑��݂��Ȃ�
		return null;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		// �Ȃɂ����Ȃ��B
	}
	
	protected String getData() {
		return data;
	}
}
