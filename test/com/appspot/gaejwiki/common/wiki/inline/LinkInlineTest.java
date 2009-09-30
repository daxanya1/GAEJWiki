package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkInlineTest {

	@Test
	public void tetWikiObjectBlockFactorySub() {
		LinkInline.Checker checker = new LinkInline.Checker();
		
		assertEquals(checker.getMatchLength("[[page]]"), 0);
		assertEquals(checker.getMatchLength("[[page]]test"), 0);
		assertEquals(checker.getMatchLength("[[pa#ge]]"), 0);
		assertEquals(checker.getMatchLength("[[paFge]]"), 0);
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("test[[page]]test"), 0);
		assertEquals(checker.getMatchLength("[[pa:ge]]"), 9);
		assertEquals(checker.getMatchLength("[[page]:]"), 0);
		assertEquals(checker.getMatchLength("[:[page]]"), 0);
		assertEquals(checker.getMatchLength("[[page>]]"), 0);
		assertEquals(checker.getMatchLength("[[page&]]"), 0);
		assertEquals(checker.getMatchLength("[[\"page]]"), 0);
		assertEquals(checker.getMatchLength("[[<page]]"), 0);
		assertEquals(checker.getMatchLength("[[page>test]]"), 13);
		assertEquals(checker.getMatchLength("[[:test]]"), 0);
		assertEquals(checker.getMatchLength("[[::test]]"), 0);
		assertEquals(checker.getMatchLength("[[t::test]]"), 11);
		assertEquals(checker.getMatchLength("[[t:http://test]]"), 17);
		assertEquals(checker.getMatchLength("[[test>#http://test]]"), 21);
	}

}
