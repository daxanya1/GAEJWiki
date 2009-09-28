package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * ��`���X�g
 * @author daxanya
 * 
 * --
 * 
�s���� : �Ŏn�߁A| �L���ŋ�؂�ƁA��`���X�g�ɂȂ�܂��B��`���X�g�� :�A::�A::: ��3�i�K����܂��B��`���X�g�̒�`��A�������͏ȗ����邱�Ƃ��ł��܂��B�����̘A��������`���X�g���L�q���A2�ڈȍ~�̒�`����ȗ����邱�Ƃ�1�̒�`��ɑ΂��镡���̐��������L�q���邱�Ƃ��ł��܂��B

�s���� | ���Ȃ��ƒ�`���X�g�ɂ͂Ȃ�܂���B
��`��E�������́A�C�����C���v�f�̂݋L�q���邱�Ƃ��ł��܂��B
��`���X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̃��X�g�\���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���p���̎q�v�f�ɂ���ꍇ�́A���x���𑝂₳���ɋL�q���܂��B
| �̒���� ~ ���L�q����ƒi�����q�v�f�ɂ��邱�Ƃ��ł��܂��B
��`���X�g�́A��`���X�g�̎��̍s�ɑ��̃u���b�N�v�f���L�q���邱�ƂŁA���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 * --
 */
public class DefinedListBlock implements WikiObjectI {

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
