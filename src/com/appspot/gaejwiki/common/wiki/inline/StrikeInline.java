package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ParentableInlineBase;

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

public class StrikeInline extends ParentableInlineBase {

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("strike");
		sb.append("|");
		if (getChildList() != null) {
			for (WikiObjectInlineI inline : getChildList()) {
				sb.append("c:/");
				sb.append(inline.toDebugString());
				sb.append("/:c");
			}
		}
		return sb.toString();
	}

	@Override
	public String getPattern() {
		return STRIKEFORMATPATTERN;
	}
	
	/**
	 * 取り消し線かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, STRIKEFORMATPATTERN);
		}
	}
}
