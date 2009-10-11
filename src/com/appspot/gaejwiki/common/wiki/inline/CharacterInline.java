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

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.common.wiki.inline.base.OneCharacterInlineBase;

/**
 * WikiObject
 * inline 文字列
 * @author daxanya
 *
 *
 *文字列はインライン要素です。

文字列は、他のインライン要素の子要素になることができます。
文字列は、他のインライン要素を子要素にすることができません。
 *
 */

public class CharacterInline extends OneCharacterInlineBase {

	@Override
	public String toHtmlString() {
		return new TextUtils().escapeHtmlString(getRawData());
	}

	@Override
	public String toDebugString() {
		return toString();
	}
	
	@Override
	public String toString() {
		return getRawData();
	}
	
	/**
	 * 文字列を１文字とする
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return 1;
		}
	}

}
