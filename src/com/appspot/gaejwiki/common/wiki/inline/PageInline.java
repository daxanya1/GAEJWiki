/**
 Copyright 2009 GAEJWiki Team.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
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
 *
 * 内部動作
 * ページがあるかないかの確認は、inlineのパース時に確認し、ない場合はフラグをセットしておきます。
 * フラグがセットされている場合、?の文字を付加します。
 */

public class PageInline extends ChildOnlyInlineBase {

	private boolean existpage = false;
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		if (existpage) {
			sb.append(new Sub().getExistHtmlString(toString(), getAccessPageName()));
		} else {
			sb.append(new Sub().getNonExistHtmlString(toString(), getAccessPageName()));
		}
		return sb.toString();
	}
	

	@Override
	protected void checkPage(WikiInlineParser parser) {
		existpage = parser.checkPage(toString());
		
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("page");
		sb.append("|");
		sb.append(toString());
		return sb.toString();
	}
	
	@Override
	public String getPattern() {
		return PAGEFORMATPATTERN;
	}
	
	static public class Checker implements WikiObjectInlineI.Checker {

		@Override
		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, PAGEFORMATPATTERN);
		}
	}

}
