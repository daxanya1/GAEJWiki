package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PageInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		PageInline.Checker checker = new PageInline.Checker();
		
		assertEquals(checker.getMatchLength("[[page]]"), 8);
		assertEquals(checker.getMatchLength("[[page]]test"), 8);
		assertEquals(checker.getMatchLength("[[pa#ge]]"), 9);
		assertEquals(checker.getMatchLength("[[paFge]]"), 9);
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("test[[page]]test"), 0);
		assertEquals(checker.getMatchLength("[[pa:ge]]"), 0);
		assertEquals(checker.getMatchLength("[[page]:]"), 0);
		assertEquals(checker.getMatchLength("[:[page]]"), 0);
		assertEquals(checker.getMatchLength("[[page>]]"), 0);
		assertEquals(checker.getMatchLength("[[page&]]"), 0);
		assertEquals(checker.getMatchLength("[[\"page]]"), 0);
		assertEquals(checker.getMatchLength("[[<page]]"), 0);
	}

	@Test
	public void testPageInlineSub() {
		PageInline page1 = new PageInline();
		page1.set("[[test 1]]", null);
		assertEquals(page1.toDebugString(), "page|test 1");

		PageInline page2 = new PageInline();
		page2.set("[[test 1[[]]test]]", null);
		assertEquals(page2.toDebugString(), "page|test 1[[]]test");

	}
}
