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

	@Test
	public void testItalicInlineSub() {
		ItalicInline inline1 = new ItalicInline();
		WikiObjectInlineI.Util sub1 = new WikiObjectInlineI.Util();
		String line1 = sub1.matchSet("'''test 1'''", inline1.getPattern());
		assertEquals(line1, "test 1");
		assertEquals(inline1.toDebugString(), "italic|");

		ItalicInline inline2 = new ItalicInline();
		WikiObjectInlineI.Util sub2 = new WikiObjectInlineI.Util();
		String line2 = sub2.matchSet("'''test 1'''test'''", inline1.getPattern());
		assertEquals(line2, "test 1'''test");
		assertEquals(inline2.toDebugString(), "italic|");

	}
}
