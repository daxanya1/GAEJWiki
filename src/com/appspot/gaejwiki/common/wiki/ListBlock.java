package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * 番号なしリスト, 番号ありリスト
 * @author daxanya
 *
 * --
 *
行頭で - を指定すると、番号なしリストになります。番号なしリストは -、--、--- の3レベルあります。

番号なしリストは、他のブロック要素の子要素になることができます。他のリスト構造の子要素にする場合は、レベルを1段増やして記述します。引用文の子要素にする場合は、レベルを増やさずに記述します。
-の直後に ~を記述すると段落を子要素にすることができます。
番号なしリストは、リストの先頭がインライン要素または段落である場合に限り、リストの次の行に他のブロック要素を記述することで、他のブロック要素を子要素にすることができます。

行頭で + を指定すると、番号付きリストになります。番号付きリストは +、++、+++ の3レベルあります。

番号付きリストは、他のブロック要素の子要素になることができます。他のリスト構造の子要素にする場合は、レベルを1段増やして記述します。引用文の子要素にする場合は、レベルを増やさずに記述します。
+ の直後に ~ を記述すると段落を子要素にすることができます。
番号付きリストは、リストの先頭がインライン要素または段落である場合に限り、リストの次の行に他のブロック要素を記述することで、他のブロック要素を子要素にすることができます。
 *
 * --
 */
public class ListBlock implements WikiObjectI {

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
