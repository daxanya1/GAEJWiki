package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

/**
 * WikiObject
 * 引用
 * @author daxanya
 *
 * --
 *
行頭で > を指定すると、引用文になります。引用文は >、>>、>>> の3レベルあります。

引用文の中は、ブロック要素を明示しない限り、段落となります。
引用文は、空行が現われるまで継続します。
引用文内の段落は、新たな引用文またはブロック要素が現われるまで継続します。
引用文は、他のブロック要素の子要素になることができます。他の引用文の子要素にする場合は、レベルを1段増やして記述します。リスト構造の子要素にする場合はレベルを1段増やさずに記述します。
引用文は、他のブロック要素を子要素にすることができます。引用文の子要素となるリスト構造はレベルを1段増やさずに記述します。
リスト構造内の引用文から脱出する場合で、リスト構造を継続する場合は、<、<<、<<<を行頭に記述します。
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
