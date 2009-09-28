package com.appspot.gaejwiki.common.wiki;



/**
 * WikiObject
 * 水平線
 * @author daxanya
 *
 * --
 * 
行頭で4つ以上の - を書くと水平線になります。

水平線は、他のブロック要素の子要素になることはできません。水平線が現われると他のブロック要素は終了します。
水平線は、他のブロック要素を子要素にすることはできません。
 * 
 * --

 */
public class HorizonBlock implements WikiObjectI {

	private String horizon = new String("");
	
	@Override
	public void addLine(String str) {
		horizon = str;
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
		return null;
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
	
	protected String getData() {
		return horizon;
	}

}
