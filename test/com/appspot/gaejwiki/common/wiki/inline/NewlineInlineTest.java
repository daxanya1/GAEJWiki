package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NewlineInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		NewlineInline.Checker checker = new NewlineInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("~"), 1);
		assertEquals(checker.getMatchLength("a~"), 0);
		assertEquals(checker.getMatchLength("~ "), 0);
	}

	@Test
	public void testNewlineInlineSub() {
		NewlineInline inline1 = new NewlineInline();
		inline1.set("~", null);
		assertEquals(inline1.toDebugString(), "<br />");
	}
}
