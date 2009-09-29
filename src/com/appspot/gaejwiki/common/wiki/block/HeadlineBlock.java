package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;



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
public class HeadlineBlock extends NoChildParentBlockBase {

	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * 見出しどうかチェックする
		 * HEADLINE要素が一文字目であれば、見出しとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 見出しであればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// HEADLINE要素が一文字目であれば、見出しとする
			return (line.charAt(0) == HEADLINE) ? true : false;
		}
	}
}
