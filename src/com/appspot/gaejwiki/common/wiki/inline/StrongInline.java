package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ParentableInlineBase;

/**
 * WikiObject
 * inline 強調
 * @author daxanya
 *
 *
 *行中のインライン要素を '' ではさむと、インライン要素が 強調表示 になります。

強調は、他のインライン要素の子要素になることができます。
強調は、他のインライン要素を子要素にすることができます。

 *
 */

public class StrongInline extends ParentableInlineBase {

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("strong");
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
		return STRONGFORMATPATTERN;
	}
	
	
	/**
	 * 強調かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, STRONGFORMATPATTERN);
		}
	}
}
