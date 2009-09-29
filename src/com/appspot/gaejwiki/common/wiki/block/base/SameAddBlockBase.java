package com.appspot.gaejwiki.common.wiki.block.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;



/**
 * WikiObject
 * 同系統のブロックが連続していれば、行追加できる
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public class SameAddBlockBase implements WikiObjectBlockI {

	private WikiObjectBlockI parent = null;
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
	public boolean isSameBlockAddLine() {
		return true;
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
		// なにもしない。
	}

	@Override
	public WikiObjectBlockI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectBlockI wikiobject) {
		parent = wikiobject;
	}
	
	protected List<String> getRawlist() {
		return rawlist;
	}

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		for (String str : rawlist) {
			sb.append(str);
			sb.append("\n");
		}
		return sb.toString();
	}
}
