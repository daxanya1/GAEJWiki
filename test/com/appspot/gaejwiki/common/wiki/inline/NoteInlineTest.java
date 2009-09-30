package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NoteInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		NoteInline.Checker checker = new NoteInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("(())"), 0);
		assertEquals(checker.getMatchLength("((test))"), 8);
		assertEquals(checker.getMatchLength("a((a))"), 0);
		assertEquals(checker.getMatchLength("((a)) "), 5);
		assertEquals(checker.getMatchLength("((a))) "), 6);
		assertEquals(checker.getMatchLength("(((a))) "), 7);
		assertEquals(checker.getMatchLength("( ((a))) "), 0);
		assertEquals(checker.getMatchLength("((a () ))) "), 10);
	}

}
