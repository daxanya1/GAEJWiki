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
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

/**
 * WikiObject
 * 段落
 * @author daxanya
 * 
 * --
 * 
他のブロック要素を明示しない限り、段落となります。

行頭で ~ を指定した場合も段落になります。行頭書式の文字(~、-、+、:、>、|、#、//)を通常の文字として段落の先頭に書きたい場合は、行頭に~を記述して書くことができます。

段落の先頭は1文字分字下げされます。但し、番号なしリスト構造、番号付きリスト構造、引用文内の段落では字下げされません。定義リスト内の段落の先頭は1文字分字下げされます。
段落は、新たなブロック要素が現われるまで継続します。
段落は、他のブロック要素の子要素になることができます。
段落は、他のブロック要素を子要素にすることはできません。
 *
 * --
 */
public class ParagraphBlock extends SameAddBlockBase {

	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<p>");
		for (WikiObjectInlineI inline : inlinelist) {
			sb.append(inline.toHtmlString());
		}
		sb.append("</p>");
		sb.append(new Util().getLineSeparator());
		return sb.toString();
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		
		List<String> datalist = getRawlist();
		if (datalist == null || datalist.size() == 0) {
			return;
		}

		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (String line : datalist) {
			if (!first) { sb.append(new Util().getLineSeparator()); } else { first = false; }
			sb.append(line);
		}
		
		inlinelist.addAll(parser.parseInline(sb.toString()));
	}

	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			// 判定せずにtrueを返す
			return true;
		}
	}

}
