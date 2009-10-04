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
		String str = "((([[‚ ‚ ‚ >https://hogehoge.jp]] ''tes&br;t123'' )))~";
		List<WikiObjectInlineI> wikilist = getTestParser().parseInline(str);
		assertEquals(wikilist.size(), 2);
		assertEquals(wikilist.get(0).toDebugString(), "note|c:/(/:cc:/true|‚ ‚ ‚ ||https://hogehoge.jp|/:cc:/ /:cc:/strong|c:/tes/:cc:/br||/:cc:/t123/:c/:cc:/ )/:c");
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
		String str = "[[—ûK‚R]]‚àc‚Á‚Ä‚¢‚éH ";
		List<WikiObjectInlineI> wikilist = getTestParser().parseInline(str);
		assertEquals(wikilist.size(), 2);
		assertEquals(wikilist.get(0).toDebugString(), "page|—ûK‚R");
		assertEquals(wikilist.get(1).toDebugString(), "‚àc‚Á‚Ä‚¢‚éH ");
	}
	
	
}
