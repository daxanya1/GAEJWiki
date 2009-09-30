package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildParentInlineBase;

/**
 * WikiObject
 * inline 注釈
 * @author daxanya
 *
 *
 *行中でインライン要素を (( と )) ではさむと、注釈*3が作成され、行中に注釈へのリンクが貼られます。

注釈は、他のインライン要素の子要素になることができます。親要素は注釈文ではなく、注釈へのリンクに反映されます。
注釈は、他のインライン要素を子要素にすることができます。子要素は注釈文に反映されます。
 *
 */

public class NoteInline extends YesChildParentInlineBase {

	/**
	 * 注釈かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, NOTEFORMATPATTERN);
		}
	}
}
