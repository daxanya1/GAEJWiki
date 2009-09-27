package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * ���p
 * @author daxanya
 *
 * --
 *
�s���� > ���w�肷��ƁA���p���ɂȂ�܂��B���p���� >�A>>�A>>> ��3���x������܂��B

���p���̒��́A�u���b�N�v�f�𖾎����Ȃ�����A�i���ƂȂ�܂��B
���p���́A��s��������܂Ōp�����܂��B
���p�����̒i���́A�V���Ȉ��p���܂��̓u���b�N�v�f��������܂Ōp�����܂��B
���p���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̈��p���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���X�g�\���̎q�v�f�ɂ���ꍇ�̓��x����1�i���₳���ɋL�q���܂��B
���p���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B���p���̎q�v�f�ƂȂ郊�X�g�\���̓��x����1�i���₳���ɋL�q���܂��B
���X�g�\�����̈��p������E�o����ꍇ�ŁA���X�g�\�����p������ꍇ�́A<�A<<�A<<<���s���ɋL�q���܂��B
 *
 * --
 */
public class QuotationBlock implements WikiObjectI {

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
		StringBuffer sb = new StringBuffer();
		for (String str : rawlist) {
			// sb.append(WikiObjectBlockFactory.QUOTATION);
			sb.append(str);
			sb.append("\n");
		}
		for (WikiObjectI wikiobj : childlist) {
			sb.append("/c:");
			sb.append(wikiobj.toString());
			sb.append(":c/");
		}
		return sb.toString();
	}

	@Override
	public WikiObjectI getParent() {
		return parent;
	}
	

	@Override
	public void setParent(WikiObjectI wikiobject) {
		parent = wikiobject;
	}
}
