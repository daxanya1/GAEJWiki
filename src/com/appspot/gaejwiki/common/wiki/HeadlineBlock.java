package com.appspot.gaejwiki.common.wiki;


/**
 * WikiObject
 * ���o��
 * @author daxanya
 *
 * --
 * 
�s���� * ���L�q����ƁA���o���ɂȂ�܂��B���o���� *�A**�A*** ��3�i�K����܂��B

���o���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ͂ł��܂���B���o����������Ƒ��̃u���b�N�v�f�͏I�����܂��B
���o���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 * 
 * --

 */
public class HeadlineBlock implements WikiObjectI {

	private String headline = new String("");
	
	@Override
	public void addLine(String str) {
		headline = str;
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
		return headline + "\n";
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
}
