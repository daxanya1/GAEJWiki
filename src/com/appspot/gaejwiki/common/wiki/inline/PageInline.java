package com.appspot.gaejwiki.common.wiki.inline;

import java.util.HashMap;
import java.util.Map;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildNoParentInlineBase;

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

public class PageInline extends YesChildNoParentInlineBase {

	
	/**
	 * ページ名かどうか確認
	 * @param str 文字列
	 * @return ページ名ならtrue
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public static final HashMap<Character, Boolean> PAGENOTINCLUDESMAP = new HashMap<Character, Boolean>();
		
		static {
			for (char includes : PAGENOTINCLUDES) {
				PAGENOTINCLUDESMAP.put(new Character(includes), Boolean.TRUE);
			}
		}
	
		public boolean isThis(String str) {
			if (str == null || str.length() == 0) {
				return false;
			}
			
			Sub sub = new Sub();
			// ページ名形式かつ、中に":&<>が含まれていないケースのみ該当 ※ #はアンカーなのではじかない
			return (sub.isPageFormat(str) && !(sub.isIncludeChars(str, PAGENOTINCLUDESMAP))) ? true : false;
		}
	}
	
	static public class Sub {
		
		/**
		 * ページ形式([[...]])かどうかを判断（PageInlineの条件とは異なり、外側の[[, ]]のみをチェック）
		 * @param str
		 * @return ページ形式であればtrue
		 */
		public boolean isPageFormat(String str) {
			
			// ページ形式であれば5文字は必要
			if (str == null || str.length() < 5) {
				return false;
			}
			
			return (PAGEFORMATBEGIN.equals(str.substring(0, 2)) && 
					PAGEFORMATEND.equals(str.substring(str.length()-2, 2))) ? true : false;
		}
		
		/**
		 * strを１文字ずつ解析し、charsに含まれている文字があれば、true
		 * @param str
		 * @param chars Map
		 * @return Map内の文字がstrに含まれていればtrue
		 */
		public boolean isIncludeChars(String str, Map<Character, Boolean> chars) {
			for (char ch : str.toCharArray()) {
				if (chars.containsKey(new Character(ch))) {
					return true;
				}
			}
			return false;
		}

	}
}
