package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.*;

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

}
