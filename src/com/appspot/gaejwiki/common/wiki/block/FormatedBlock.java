package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;

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
public class FormatedBlock extends SameAddBlockBase  {

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * 整形済み文字列かどうかチェックする
		 * FORMATED要素が一文字目であれば、整形済み文字列とする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 整形済み文字列であればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// FORMATED要素が一文字目であれば、整形済み文字列とする
			return (line.charAt(0) == FORMATED) ? true : false;
		}
	}
}
