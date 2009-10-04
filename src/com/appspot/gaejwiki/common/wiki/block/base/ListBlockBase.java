package com.appspot.gaejwiki.common.wiki.block.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;



/**
 * WikiObject
 * リスト系
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public abstract class ListBlockBase implements WikiObjectBlockI {

	private WikiObjectBlockI parent = null;
	private List<String> rawlist = new ArrayList<String>();
	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	
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

	@Override
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		List<String> cutlist = cutDataList(rawlist);
		
		for (String str : cutlist) {
			inlinelist.addAll(parser.parseInline(str));
		}
		for (WikiObjectBlockI wikiobj : childlist) {
			wikiobj.paserInline(parser);
		}
	}
	
	/**
	 * ブロックの先頭部分を切る
	 * @param datalist
	 * @return 先頭部分を切った文字列の配列
	 */
	abstract protected List<String> cutDataList(List<String> datalist);

}
