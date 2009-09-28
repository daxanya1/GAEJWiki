package com.appspot.gaejwiki.common.wiki;



/**
 * WikiObject
 * ������
 * @author daxanya
 *
 * --
 * 
�s����4�ȏ�� - �������Ɛ������ɂȂ�܂��B

�������́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ͂ł��܂���B��������������Ƒ��̃u���b�N�v�f�͏I�����܂��B
�������́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 * 
 * --

 */
public class HorizonBlock implements WikiObjectI {

	private String horizon = new String("");
	
	@Override
	public void addLine(String str) {
		horizon = str;
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
	public boolean isAddToParent() {
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
		// �e�͑��݂��Ȃ�
		return null;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		// �Ȃɂ����Ȃ��B
	}
	
	protected String getData() {
		return horizon;
	}

}
