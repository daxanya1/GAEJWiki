package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItalicInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		ItalicInline.Checker checker = new ItalicInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("'''test'''"), 10);
		assertEquals(checker.getMatchLength("''test''"), 0);
		assertEquals(checker.getMatchLength("'''test''''"), 11);
		assertEquals(checker.getMatchLength("'''test'''test'''"), 17);
		assertEquals(checker.getMatchLength("a'''test'''test'''"), 0);
		assertEquals(checker.getMatchLength("'''test''"), 0);
		assertEquals(checker.getMatchLength("''''''"), 0);
		assertEquals(checker.getMatchLength("'''test'''a"), 10);
	}

}
