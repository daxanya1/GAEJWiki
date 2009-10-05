package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ChildOnlyInlineBase;

/**
 * WikiObject
 * inline WikiName
 * @author daxanya
 *
 *
 *行中で、1つ以上の大文字→1つ以上の小文字→1つ以上の大文字→1つ以上の小文字の組合わせからなる半角//文字列はWikiNameになります。

WikiNameの中には、全角文字や半角空白文字、記号、数字を含めることはできません。
WikiNameは、PukiWiki内のページ名になります。すでに存在するページであればそのページへのリンクが自動的に貼られます。存在しない場合はWikiNameの後ろに?が自動的に付き、そのページを新規作成するためのリンクが貼られます。
WikiNameは、他のインライン要素の子要素になることができます。
WikiNameは、他のインライン要素を子要素にはできません。
 *
 * * 内部動作
 * ページがあるかないかの確認は、inlineのパース時に確認し、ない場合はフラグをセットしておきます。
 * フラグがセットされている場合、?の文字を付加します。(pageと同じ動作)
 */

public class WikiNameInline extends ChildOnlyInlineBase {

	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("wikiname");
		sb.append("|");
		sb.append(toString());
		sb.append("|");
		return sb.toString();
	}
	

	@Override
	protected void checkPage(WikiInlineParser parser) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getPattern() {
		return WIKINAMEFORMATPATTERN;
	}
	
	/**
	 * WikiNameかどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, WIKINAMEFORMATPATTERN);
		}
	}

}
