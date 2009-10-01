package com.appspot.gaejwiki.common.wiki.inline;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WikiObject
 * inline &(){}系 のうち、子要素になるし親にもなるもの
 * @author daxanya
 *
 *
 *行中に &color と書くとインライン要素の文字色と背景色を指定することができます。背景色は省略できます。

色の指定は、次のいずれかの形式で行ないます。
色キーワード
#16進数6桁
#16進数3桁
文字色は、他のインライン要素の子要素になることができます。
文字色は、他のインライン要素を子要素にすることができます。

行中に &size を書くとインライン要素の文字サイズを指定することができます。サイズはピクセル単位(px)で指定します。例えば20を指定すると、20ピクセルの文字の大きさになります。

文字サイズは、他のインライン要素の子要素になることができます。
文字サイズは、他のインライン要素を子要素にすることができます。

行中で &ruby を書くとインライン要素に対するルビをふることができます。ルビに対応していないブラウザではルビが本文中に ( と ) に囲まれて表示されます。

ルビ構造のルビにはインライン要素を記述します。ただし、ルビの特性上、期待通りの表示になるとは限りません。
ルビ構造は、他のインライン要素の子要素になることができます。
ルビ構造は、他のインライン要素を子要素にすることができます。
ルビ構造をルビ構造の子要素にはできません(ネストはできません)。
注釈内や文字サイズを小さくしている部分での使用は、ルビが判読できなくなるので避けてください。

行中で &aname を記述するとリンクのアンカーを設定することができます。リンクの飛び先にしたい位置に記述します。

アンカー名は、半角アルファベットから始まる半角アルファベット・数字・ハイフン・アンダースコアからなる文字列を指定します。
アンカー名の中には、全角文字や半角空白文字、半角記号を含めることはできません。
アンカー設定は、他のインライン要素の子要素になることができます。
アンカー設定は、他のインライン要素を子要素にすることができます。

 *
 */

public class AmpersandChildParentInline implements WikiObjectInlineI {

	private String rawdata = null;
	private List<WikiObjectInlineI> childlist = null;
	private String ampersandtype = null;
	private String ampersandparam = null;
	private WikiObjectInlineI parent = null;
	
	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}
	
	@Override
	public void set(String str, WikiObjectInlineFactory factory) {
		rawdata = str;
		// 再帰処理を行う
		String line = new Sub().matchSet(rawdata);
		if (line != null) {
			childlist = new WikiInlineParser().parseInline(factory, line);
		}
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append(ampersandtype);
		sb.append("|");
		sb.append(ampersandparam);
		sb.append("|");
		if (childlist != null) {
			for (WikiObjectInlineI inline : childlist) {
				sb.append("c:/");
				sb.append(inline.toDebugString());
				sb.append("/:c");
			}
		}
		return sb.toString();
	}

	public class Sub {
		
		// 正規表現にかけて、必要な情報を取り出す
		public String matchSet(String str) {
			Pattern pattern1 = Pattern.compile(AMPERSANDCHILDPARENTFORMATPATTERN1);
			Matcher matcher1 = pattern1.matcher(str);
			if (matcher1.find()) {
				return matchSet1(matcher1);
			}

			Pattern pattern2 = Pattern.compile(AMPERSANDCHILDPARENTFORMATPATTERN2);
			Matcher matcher2 = pattern2.matcher(str);
			if (matcher2.find()) {
				return matchSet2(matcher2);
			}

			Pattern pattern3 = Pattern.compile(AMPERSANDCHILDPARENTFORMATPATTERN3);
			Matcher matcher3 = pattern3.matcher(str);
			if (matcher3.find()) {
				return matchSet2(matcher3);
			}

			
			return null;
		}
		
		public String matchSet1(Matcher matcher) {
			if (matcher.groupCount() >= 1) {
				ampersandtype = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				ampersandparam = matcher.group(2);
			}

			return (matcher.groupCount() >= 3) ? matcher.group(3) : null;
		}
		
		public String matchSet2(Matcher matcher) {
			if (matcher.groupCount() >= 1) {
				ampersandtype = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				ampersandparam = matcher.group(2);
			}

			return (matcher.groupCount() >= 4) ? matcher.group(4) : null;
		}
	}

	/**
	 * &(){}系かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		@Override
		public int getMatchLength(String str) {
			int length = 0;
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN1);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN2);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN3);
			if (length > 0) { return length; }
			
			return 0;
		}
	}

}
