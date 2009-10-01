package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ChildOnlyInlineBase;

/**
 * WikiObject
 * inline 文字列
 * @author daxanya
 *
 *
 *文字列はインライン要素です。

文字列は、他のインライン要素の子要素になることができます。
文字列は、他のインライン要素を子要素にすることができません。
 *
 */

public class CharacterInline extends ChildOnlyInlineBase {

	/**
	 * 文字列を１文字とする
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return 1;
		}
	}
}
