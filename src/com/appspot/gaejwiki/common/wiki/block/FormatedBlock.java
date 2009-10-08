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
package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;

/**
 * WikiObject
 * 整形済みテキスト
 * 
 * --
 * 
行頭が半角空白で始まる行は整形済みテキストとなります。行の自動折り返しは行なわれません。

整形済みテキストは、他のブロック要素の子要素になることができます。
整形済みテキストは、他のブロック要素を子要素にすることができません。
整形済みテキストは、すべての子要素を文字列として扱います。
 * 
 * --
 * 
 * 
 * @author daxanya
 *
 */
public class FormatedBlock extends SameAddBlockBase  {

	private List<String> cutlist = new ArrayList<String>();
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<pre>");
		for (int i=0; i<cutlist.size()-1;i++) {
			sb.append(cutlist.get(i));
			sb.append(new Util().getLineSeparator());
		}
		sb.append(cutlist.get(cutlist.size()-1));
		sb.append("</pre>");
		sb.append(new Util().getLineSeparator());
		return sb.toString();
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		List<String> datalist = getRawlist();
		if (datalist == null || datalist.size() == 0) {
			return;
		}

		for (int i = 0; i < datalist.size(); i++) {
			cutlist.add(new Util().cutFrontChar(datalist.get(i), 1));
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (line.charAt(0) == FORMATED) ? true : false;
		}
	}
}
