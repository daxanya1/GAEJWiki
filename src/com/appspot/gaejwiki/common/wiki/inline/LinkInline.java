package com.appspot.gaejwiki.common.wiki.inline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class LinkInline  implements WikiObjectInlineI {

	private String rawdata = null;
	private String alias = null;
	private String interwiki = null;
	private String param = null;
	private WikiObjectInlineI parent = null;
	private boolean urlflag = false;
	
	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}
	
	@Override
	public void set(String str, WikiInlineParser parser) {
		rawdata = str;
		new Sub().matchSet(rawdata);

	}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public String toHtmlString() {
		return "";
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append(new Boolean(urlflag).toString());
		sb.append("|");
		if (alias != null) sb.append(alias);
		sb.append("|");
		if (interwiki != null) sb.append(interwiki);
		sb.append("|");
		if (param != null) sb.append(param);
		sb.append("|");
		return sb.toString();
	}
	
	public class Sub {
		
		// 正規表現にかけて、必要な情報を取り出す
		public void matchSet(String str) {
			{
				Pattern pattern1 = Pattern.compile(LINKFORMATPATTERN1);
				Matcher matcher1 = pattern1.matcher(str);
				if (matcher1.find()) {
					matchSet1(matcher1);
					return;
				}
			}

			{
				Pattern pattern2 = Pattern.compile(LINKFORMATPATTERN2);
				Matcher matcher2 = pattern2.matcher(str);
				if (matcher2.find()) {
					matchSet1(matcher2);
					return;
				}
			}

			{
				Pattern pattern3 = Pattern.compile(LINKFORMATPATTERN3);
				Matcher matcher3 = pattern3.matcher(str);
				if (matcher3.find()) {
					matchSet2(matcher3);
					return;
				}
			}

			{
				Pattern pattern4 = Pattern.compile(LINKFORMATPATTERN4);
				Matcher matcher4 = pattern4.matcher(str);
				if (matcher4.find()) {
					matchSet2(matcher4);
					return;
				}
			}

			{
				Pattern pattern5 = Pattern.compile(LINKFORMATPATTERN5);
				Matcher matcher5 = pattern5.matcher(str);
				if (matcher5.find()) {
					matchSet3(matcher5);
					return;
				}
			}

			{
				Pattern pattern6 = Pattern.compile(LINKFORMATPATTERN6);
				Matcher matcher6 = pattern6.matcher(str);
				if (matcher6.find()) {
					matchSet4(matcher6);
					return;
				}
			}
			
			return;
		}
		
		public void matchSet1(Matcher matcher) {
			urlflag = true;
			if (matcher.groupCount() >= 1) {
				alias = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				param = matcher.group(2);
			}
		}

		
		public void matchSet2(Matcher matcher) {
			urlflag = true;
			if (matcher.groupCount() >= 1) {
				param = matcher.group(1);
			}
		}
		
		public void matchSet3(Matcher matcher) {
			urlflag = false;
			if (matcher.groupCount() >= 1) {
				alias = matcher.group(1);
			}
			if (matcher.groupCount() >= 3) {
				interwiki = matcher.group(3);
			}
			if (matcher.groupCount() >= 4) {
				param = matcher.group(4);
			}
		}

		public void matchSet4(Matcher matcher) {
			urlflag = false;
			if (matcher.groupCount() >= 1) {
				interwiki = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				param = matcher.group(2);
			}
		}

	}

	/**
	 * リンクかどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			int length = 0;
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN1);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN2);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN3);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN4);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN5);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN6);
			if (length > 0) { return length; }

			
			return 0;
		}
	}

}
