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
	public String getDebugStringHeader() {
		return "strong";
	}

	@Override
	public String toHtmlStringHeader() {
		return "<strong>";
	}

	@Override
	public String toHtmlStringFooter() {
		return "</strong>";
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
