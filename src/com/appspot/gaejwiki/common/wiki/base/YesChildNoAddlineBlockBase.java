package com.appspot.gaejwiki.common.wiki.base;


/**
 * WikiObject
 * �q���ɂ͂Ȃ�邪�s�ǉ����ł��Ȃ�
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public class YesChildNoAddlineBlockBase implements WikiObjectI {

	private WikiObjectI parent = null;
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
	public void addChildBlock(WikiObjectI wikiobject) {
		// �Ȃɂ����Ȃ��B
	}

	@Override
	public WikiObjectI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		parent = wikiobject;
	}

	protected String getData() {
		return data;
	}
}
