package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildParentInlineBase;

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

public class AmpersandChildParentInline extends YesChildParentInlineBase {

	/**
	 * &(){}系かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		@Override
		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN);
		}
	}
}
