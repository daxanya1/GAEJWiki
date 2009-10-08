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
 * inline ����
 * @author daxanya
 *
 *
 *�s���̃C�����C���v�f�� '' �ł͂��ނƁA�C�����C���v�f�� �����\�� �ɂȂ�܂��B

�����́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�����́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B

 *
 */

public class StrongInline extends ParentableInlineBase {

	@Override
	public String getDebugStringHeader() {
		return "strong";
	}

	@Override
	public String toHtmlStringHeader() {
		return "<strong>";
	}

	@Override
	public String toHtmlStringFooter() {
		return "</strong>";
	}

	@Override
	public String getPattern() {
		return STRONGFORMATPATTERN;
	}
	
	
	/**
	 * �������ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, STRONGFORMATPATTERN);
		}
	}
}
