package com.appspot.gaejwiki.common.wiki.block;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;

/**
 * WikiObject
 * 番号ありリスト
 * @author daxanya
 *
 * --
 *
行頭で + を指定すると、番号付きリストになります。番号付きリストは +、++、+++ の3レベルあります。

番号付きリストは、他のブロック要素の子要素になることができます。他のリスト構造の子要素にする場合は、レベルを1段増やして記述します。引用文の子要素にする場合は、レベルを増やさずに記述します。
+ の直後に ~ を記述すると段落を子要素にすることができます。
番号付きリストは、リストの先頭がインライン要素または段落である場合に限り、リストの次の行に他のブロック要素を記述することで、他のブロック要素を子要素にすることができます。
 *
 * --
 */
public class NumberedListBlock extends ListBlockBase {

	@Override
	protected String toHtmlStringFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String toHtmlStringHeader() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String cutBlockChar(List<String> datalist) {
		return new Util().cutBlockCharListBlockBase(this, datalist, NUMBEREDLIST);
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (line.charAt(0) == NUMBEREDLIST) ? true : false;
		}
	}
}
