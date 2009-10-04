package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ChildOnlyInlineBase;

/**
 * WikiObject
 * inline ページ名
 * @author daxanya
 *
 *
 *行中で [[ と ]] で囲まれた文字列はページ名になります。

ページ名の中には、全角文字、記号、数字、半角空白文字を含めることができます。
ページ名の中には、"#&<> を含めることはできません。
すでに存在するページであればそのページへのリンクが自動的に貼られます。存在しない場合はページ名の後ろに?が自動的に付き、そのページを新規作成するためのリンクが貼られます。
[[ページ名#アンカー名]]
ページ名にアンカー名をつけることもできます。
ページ名は、他のインライン要素の子要素になることができます。
ページ名は、他のインライン要素を子要素にはできません。
 *
 */

public class PageInline extends ChildOnlyInlineBase {

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("page");
		sb.append("|");
		sb.append(getData());
		return sb.toString();
	}
	
	@Override
	public String getPattern() {
		return PAGEFORMATPATTERN;
	}
	
	/**
	 * ページ名かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, PAGEFORMATPATTERN);
		}
	}
}
