package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StrikeInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		StrikeInline.Checker checker = new StrikeInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("%test%"), 6);
		assertEquals(checker.getMatchLength("%%"), 0);
		assertEquals(checker.getMatchLength("%1% "), 3);
		assertEquals(checker.getMatchLength(" %1% "), 0);
		assertEquals(checker.getMatchLength("%1% % "), 5);

	}

}
