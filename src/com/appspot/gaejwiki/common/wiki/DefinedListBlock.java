package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * 定義リスト
 * @author daxanya
 * 
 * --
 * 
行頭を : で始め、| 記号で区切ると、定義リストになります。定義リストは :、::、::: の3段階あります。定義リストの定義語、説明文は省略することができます。複数の連続した定義リストを記述し、2つ目以降の定義語を省略することで1つの定義語に対する複数の説明文を記述することができます。

行中に | がないと定義リストにはなりません。
定義語・説明文は、インライン要素のみ記述することができます。
定義リストは、他のブロック要素の子要素になることができます。他のリスト構造の子要素にする場合は、レベルを1段増やして記述します。引用文の子要素にする場合は、レベルを増やさずに記述します。
| の直後に ~ を記述すると段落を子要素にすることができます。
定義リストは、定義リストの次の行に他のブロック要素を記述することで、他のブロック要素を子要素にすることができます。
 *
 * --
 */
public class DefinedListBlock implements WikiObjectI {

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
