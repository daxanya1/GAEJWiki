package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * ���`�ς݃e�L�X�g
 * 
 * --
 * 
�s�������p�󔒂Ŏn�܂�s�͐��`�ς݃e�L�X�g�ƂȂ�܂��B�s�̎����܂�Ԃ��͍s�Ȃ��܂���B

���`�ς݃e�L�X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
���`�ς݃e�L�X�g�́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B
���`�ς݃e�L�X�g�́A���ׂĂ̎q�v�f�𕶎���Ƃ��Ĉ����܂��B
 * 
 * --
 * 
 * @author daxanya
 *
 */
public class FormatedBlock implements WikiObjectI {

	private WikiObjectI parent = null;
	private List<String> rawlist = new ArrayList<String>();
	
	@Override
	public void addLine(String str) {
		rawlist.add(str);
	}
	
	@Override
	public boolean isAddChildBlock() {
		return false;
	}
	
	@Override
	public boolean isAddLine() {
		return true;
	}
	
	@Override
	public boolean isAddToParent() {
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
		return parent;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		parent = wikiobject;
	}

	protected List<String> getRawlist() {
		return rawlist;
	}


}
