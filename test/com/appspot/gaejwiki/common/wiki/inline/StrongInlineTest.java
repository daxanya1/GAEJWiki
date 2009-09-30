package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StrongInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		StrongInline.Checker checker = new StrongInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("''''"), 0);
		assertEquals(checker.getMatchLength("''test''"), 8);
		assertEquals(checker.getMatchLength("' ''t''"), 0);
		assertEquals(checker.getMatchLength("'' ' ''"), 7);
		assertEquals(checker.getMatchLength("'' test '' "), 10);
	}

}
