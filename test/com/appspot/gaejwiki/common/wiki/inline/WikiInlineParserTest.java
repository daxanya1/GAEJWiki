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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;


public class WikiInlineParserTest {

	public WikiInlineParser getTestParser() {
		WikiInlineParser parser = new WikiInlineParser();
		parser.setWikiObjectInlineFactory(new WikiObjectInlineFactory());
		parser.setWikiObjectBlockInfo(new WikiObjectBlockInfo());
		return parser;
	}
	
	
	
	@Test
	public void testWikiInlineParser1() {
		String str = "test1&br;[[test2]]''te'''test3'''st4";
		List<WikiObjectInlineI> wikilist = getTestParser().parseInline(str);
		assertEquals(wikilist.size(), 5);
		assertEquals(wikilist.get(0).toDebugString(), "test1");
		assertEquals(wikilist.get(1).toDebugString(), "br||");
		assertEquals(wikilist.get(2).toDebugString(), "page|test2");
		assertEquals(wikilist.get(3).toDebugString(), "strong|c:/te'''test3'/:c");
		assertEquals(wikilist.get(4).toDebugString(), "st4");
	}
	
	@Test
	public void testWikiInlineParser2() {
		String str = "((([[あああ>https://hogehoge.jp]] ''tes&br;t123'' )))~";
		List<WikiObjectInlineI> wikilist = getTestParser().parseInline(str);
		assertEquals(wikilist.size(), 2);
		assertEquals(wikilist.get(0).toDebugString(), "note|c:/(/:cc:/true|あああ||https://hogehoge.jp|/:cc:/ /:cc:/strong|c:/tes/:cc:/br||/:cc:/t123/:c/:cc:/ )/:c");
		assertEquals(wikilist.get(1).toDebugString(), "<br />");
	}
	
	@Test
	public void testWikiInlineParser3() {
		String str = "&color(a){test''test1'''test2'''test3''};";
		List<WikiObjectInlineI> wikilist = getTestParser().parseInline(str);
		assertEquals(wikilist.size(), 1);
		assertEquals(wikilist.get(0).toDebugString(), "color|a|c:/test/:cc:/strong|c:/test1/:cc:/italic|c:/test2/:c/:cc:/test3/:c/:c");
	}
	
	@Test
	public void testWikiInlineParser4() {
		String str = "[[練習３]]も残っている？ ";
		List<WikiObjectInlineI> wikilist = getTestParser().parseInline(str);
		assertEquals(wikilist.size(), 2);
		assertEquals(wikilist.get(0).toDebugString(), "page|練習３");
		assertEquals(wikilist.get(1).toDebugString(), "も残っている？ ");
	}
	
	
}
