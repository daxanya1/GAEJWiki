package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * 整形済みテキスト
 * 
 * --
 * 
行頭が半角空白で始まる行は整形済みテキストとなります。行の自動折り返しは行なわれません。

整形済みテキストは、他のブロック要素の子要素になることができます。
整形済みテキストは、他のブロック要素を子要素にすることができません。
整形済みテキストは、すべての子要素を文字列として扱います。
 * 
 * --
 * 
 * @author daxanya
 *
 */
public class FormatedBlock implements WikiObjectI {

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
		// なにもしない。
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
