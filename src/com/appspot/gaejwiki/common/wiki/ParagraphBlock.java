package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * �i��
 * @author daxanya
 * 
 * --
 * 
���̃u���b�N�v�f�𖾎����Ȃ�����A�i���ƂȂ�܂��B

�s���� ~ ���w�肵���ꍇ���i���ɂȂ�܂��B�s�������̕���(~�A-�A+�A:�A>�A|�A#�A//)��ʏ�̕����Ƃ��Ēi���̐擪�ɏ��������ꍇ�́A�s����~���L�q���ď������Ƃ��ł��܂��B

�i���̐擪��1����������������܂��B�A���A�ԍ��Ȃ����X�g�\���A�ԍ��t�����X�g�\���A���p�����̒i���ł͎���������܂���B��`���X�g���̒i���̐擪��1����������������܂��B
�i���́A�V���ȃu���b�N�v�f��������܂Ōp�����܂��B
�i���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�i���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 *
 * --
 */
public class ParagraphBlock implements WikiObjectI {

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
