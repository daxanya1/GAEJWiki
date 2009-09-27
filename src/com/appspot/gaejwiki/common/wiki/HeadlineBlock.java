package com.appspot.gaejwiki.common.wiki;


/**
 * WikiObject
 * 見出し
 * @author daxanya
 *
 * --
 * 
行頭で * を記述すると、見出しになります。見出しは *、**、*** の3段階あります。

見出しは、他のブロック要素の子要素になることはできません。見出しが現われると他のブロック要素は終了します。
見出しは、他のブロック要素を子要素にすることはできません。
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
		// なにもしない。
	}

	@Override
	public WikiObjectI getParent() {
		// 親は存在しない
		return null;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		// なにもしない。
	}
}
