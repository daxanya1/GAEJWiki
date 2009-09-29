package com.appspot.gaejwiki.common.wiki.block.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;



/**
 * WikiObject
 * ÉäÉXÉgån
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public class ListBlockBase implements WikiObjectBlockI {

	private WikiObjectBlockI parent = null;
	private List<String> rawlist = new ArrayList<String>();
	private List<WikiObjectBlockI> childlist = new ArrayList<WikiObjectBlockI>();
	
	@Override
	public void addLine(String str) {
		rawlist.add(str);
	}
	
	@Override
	public void addChildBlock(WikiObjectBlockI wikiobject) {
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
	public boolean isSameBlockAddLine() {
		return false;
	}
	
	@Override
	public boolean isReset() {
		return false;
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
	
	protected List<WikiObjectBlockI> getChildlist() {
		return childlist;
	}

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		for (String str : rawlist) {
			sb.append(str);
			sb.append("\n");
		}
		for (WikiObjectBlockI wikiobj : childlist) {
			sb.append("/c:");
			sb.append(wikiobj.toDebugString());
			sb.append(":c/");
		}
		return sb.toString();
	}

}
