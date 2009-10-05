package com.appspot.gaejwiki.common.wiki.block.base;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;



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
public abstract class YesChildNoAddlineBlockBase implements WikiObjectBlockI {

	private WikiObjectBlockI parent = null;
	private String rawdata = new String("");
	
	@Override
	public void addLine(String str) {
		rawdata = str;
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
		// �Ȃɂ����Ȃ��B
	}

	@Override
	public WikiObjectBlockI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectBlockI wikiobject) {
		parent = wikiobject;
	}


	@Override
	public String toDebugString() {
		return rawdata + "\n";
	}
	
	
	@Override
	public int getLevel() {
		// level�ݒ�͂Ȃ��̂ŁA�K��-1��Ԃ�
		return -1;
	}

	protected String getRawData() {
		return rawdata;
	}

}
