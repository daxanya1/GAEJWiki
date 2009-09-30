package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildParentInlineBase;

/**
 * WikiObject
 * inline 取り消し線
 * @author daxanya
 *
 *
 *行中のインライン要素を%%ではさむと、インライン要素に取消線が付きます。

取消線は、他のインライン要素の子要素になることができます。
取消線は、他のインライン要素を子要素にすることができます。
 *
 */

public class StrikeInline extends YesChildParentInlineBase {

	/**
	 * 取り消し線かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, STRIKEFORMATPATTERN);
		}
	}
}
