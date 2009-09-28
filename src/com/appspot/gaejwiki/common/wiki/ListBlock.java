package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * �ԍ��Ȃ����X�g, �ԍ����胊�X�g
 * @author daxanya
 *
 * --
 *
�s���� - ���w�肷��ƁA�ԍ��Ȃ����X�g�ɂȂ�܂��B�ԍ��Ȃ����X�g�� -�A--�A--- ��3���x������܂��B

�ԍ��Ȃ����X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̃��X�g�\���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���p���̎q�v�f�ɂ���ꍇ�́A���x���𑝂₳���ɋL�q���܂��B
-�̒���� ~���L�q����ƒi�����q�v�f�ɂ��邱�Ƃ��ł��܂��B
�ԍ��Ȃ����X�g�́A���X�g�̐擪���C�����C���v�f�܂��͒i���ł���ꍇ�Ɍ���A���X�g�̎��̍s�ɑ��̃u���b�N�v�f���L�q���邱�ƂŁA���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B

�s���� + ���w�肷��ƁA�ԍ��t�����X�g�ɂȂ�܂��B�ԍ��t�����X�g�� +�A++�A+++ ��3���x������܂��B

�ԍ��t�����X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̃��X�g�\���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���p���̎q�v�f�ɂ���ꍇ�́A���x���𑝂₳���ɋL�q���܂��B
+ �̒���� ~ ���L�q����ƒi�����q�v�f�ɂ��邱�Ƃ��ł��܂��B
�ԍ��t�����X�g�́A���X�g�̐擪���C�����C���v�f�܂��͒i���ł���ꍇ�Ɍ���A���X�g�̎��̍s�ɑ��̃u���b�N�v�f���L�q���邱�ƂŁA���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 * --
 */
public class ListBlock implements WikiObjectI {

	private WikiObjectI parent = null;
	private List<String> rawlist = new ArrayList<String>();
	private List<WikiObjectI> childlist = new ArrayList<WikiObjectI>();
	
	@Override
	public void addLine(String str) {
		rawlist.add(str);
	}
	
	@Override
	public void addChildBlock(WikiObjectI wikiobject) {
		childlist.add(wikiobject);
		wikiobject.setParent(this);
	}
	
	@Override
	public boolean isAddChildBlock() {
		return true;
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
	
	protected List<WikiObjectI> getChildlist() {
		return childlist;
	}

}
