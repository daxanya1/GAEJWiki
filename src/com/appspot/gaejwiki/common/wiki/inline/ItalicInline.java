package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ParentableInlineBase;

/**
 * WikiObject
 * inline 斜体
 * @author daxanya
 *
 *
 *行中のインライン要素を ''' ではさむと、インライン要素が 斜体表示 になります。

斜体は、他のインライン要素の子要素になることができます。
斜体は、他のインライン要素を子要素にすることができます。
 *
 */

public class ItalicInline extends ParentableInlineBase {


	@Override
	public String getDebugStringHeader() {
		return "italic";
	}

	@Override
	public String toHtmlStringHeader() {
		return "<em>";
	}

	@Override
	public String toHtmlStringFooter() {
		return "</em>";
	}

	@Override
	public String getPattern() {
		return ITALICFORMATPATTERN;
	}

	/**
	 * イタリックかどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, ITALICFORMATPATTERN);
		}
	}

}
