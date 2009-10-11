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

import com.appspot.gaejwiki.common.wiki.inline.base.ParentableInlineBase;

/**
 * WikiObject
 * inline 取り消し線
 * @author daxanya
 *
 *
 *行中のインライン要素を%%ではさむと、インライン要素に取消線が付きます。

取消線は、他のインライン要素の子要素になることができます。
取消線は、他のインライン要素を子要素にすることができます。
 *
 */

public class StrikeInline extends ParentableInlineBase {

	@Override
	public String getDebugStringHeader() {
		return "strike";
	}

	@Override
	public String toHtmlStringHeader() {
		return "<del>";
	}
	
	@Override
	public String toHtmlStringFooter() {
		return "</del>";
	}


	@Override
	public String getPattern() {
		return STRIKEFORMATPATTERN;
	}
	
	/**
	 * 取り消し線かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, STRIKEFORMATPATTERN);
		}
	}
}
