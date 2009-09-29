package com.appspot.gaejwiki.common.wiki.block;

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

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * リストどうかチェックする
		 * 先に水平線かどうかを調べる
		 * 水平線でなければ、NUMBEREDLIST要素かUNNUMBERED要素が一文字目であれば、リストとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return リストであればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// NUMBEREDLIST要素が一文字目であれば、番号ありリストとする
			return (line.charAt(0) == NUMBEREDLIST) ? true : false;
		}
	}
}
