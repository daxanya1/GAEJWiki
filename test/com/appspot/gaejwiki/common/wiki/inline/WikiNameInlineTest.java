package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WikiNameInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		WikiNameInline.Checker checker = new WikiNameInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("WikiName"), 8);
		assertEquals(checker.getMatchLength("Wiki1Name"), 0);
		assertEquals(checker.getMatchLength("wWikiName"), 0);
		assertEquals(checker.getMatchLength("WWWWName"), 0);
		assertEquals(checker.getMatchLength("WWWWiiiNAAAame"), 14);
		assertEquals(checker.getMatchLength("WWWWiiiNAAAame2"), 14);
		assertEquals(checker.getMatchLength("WiWiWWiiiNAAAame2"), 4);

	}

}
