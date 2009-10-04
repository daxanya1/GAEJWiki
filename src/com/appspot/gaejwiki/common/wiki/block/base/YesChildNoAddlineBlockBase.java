package com.appspot.gaejwiki.common.wiki.block.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;



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
	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	
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
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		inlinelist.addAll(parser.parseInline(cutData(rawdata)));
	}
	
	/**
	 * �u���b�N�̐擪������؂�
	 * @param data
	 * @return �擪������؂���������
	 */
	abstract protected String cutData(String data);
}
