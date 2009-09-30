package com.appspot.gaejwiki.common.wiki.inline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI.Checker.Util;
import com.appspot.gaejwiki.common.wiki.inline.base.YesChildNoParentInlineBase;

/**
 * WikiObject
 * inline Link
 * @author daxanya
 *
 *
 *行中のページ名形式の文字列の中で、: で2つの文字列を区切るとInterWikiになります。:の前にはInterWikiNameのページで定義したInterWikiNameを、: の後ろにはページ名を記述します。

[[InterWikiName:ページ名#アンカー名]]
ページ名の後ろにアンカー名をつけることもできます。
InterWikiNameの中には、全角文字を含めることができます。
InterWikiNameの中には、半角空白文字を含めるができます。
InterWikiは、他のインライン要素の子要素になることができます。
InterWikiは、他のインライン要素を子要素にはできません。
 *
 *
 *行中のInterWikiNameの形式の文字列の中で、: の前に任意のリンク名を、: の後ろにURLを記述すると、そのリンク名でURLに対するリンクを貼ることができます。

リンク名には、全角文字や半角空白文字を含めることができます。メールアドレスを指定する場合は、mailto:を書かずにアドレスだけを記述します。
http://www.example.com/
https://www.example.com/
ftp://ftp.example.com/
news://news.example.com/
foo@example.com
行中に直接、URLを記述した場合は自動的にそのURLに対するリンクが貼られます。
記述したURLが画像ファイルである場合は、その画像を表示します。
リンクは、他のインライン要素の子要素になることができます。
リンクは、他のインライン要素を子要素にはできません。
 *
 *
 *行中のページ名形式の文字列の中で、> で2つの文字列を区切るとエイリアスになります。 > の前にはエイリアス名を、> の後ろにはページ名を記述します。

エイリアスはPukiWiki内のページ名とは別のエイリアス名で、指定したページへのリンクを貼ります。

[[エイリアス名>ページ名#アンカー名]]
[[エイリアス名>#アンカー名]]
ページ名にアンカー名を付け加えてアンカーへのリンクを貼ることができます。ページ名とアンカー名の間には#をつけます。カレントページのアンカーへリンクを貼る場合はページ名を省略することができます。
[[エイリアス名>http://www.example.com/]]
[[エイリアス名>https://www.example.com/]]
[[エイリアス名>ftp://ftp.example.com/]]
[[エイリアス名>news://news.example.com/]]
[[エイリアス名>foo@example.com]]
[[エイリアス名>InterWikiName:ページ名]]
[[エイリアス名>InterWikiName:ページ名#アンカー名]]
エイリアスをURLやInterWikiと組み合わせることもできます。
[[エイリアス名:http://www.example.com/]]
[[エイリアス名:https://www.example.com/]]
[[エイリアス名:ftp://ftp.example.com/]]
[[エイリアス名:news://news.example.com/]]
[[エイリアス名:foo@example.com]]
URLを指定する場合は、>の代わりに:も使用できます。
エイリアス名の中には、全角文字を含めることができます。
エイリアス名の中には、半角空白文字を含めることができます。
階層化されたページでは、ページ名を相対指定することができます。
エイリアスは、他のインライン要素の子要素になることができます。
エイリアスは、他のインライン要素を子要素にできます。
 *
 */

public class LinkInline extends YesChildNoParentInlineBase {

	/**
	 * リンクかどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, LINKFORMATPATTERN);
		}
	}

}
