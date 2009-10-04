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

	@Test
	public void testNoteInlineSub() {
		NoteInline note1 = new NoteInline();
		WikiObjectInlineI.Util sub1 = new WikiObjectInlineI.Util();
		String line1 = sub1.matchSet("((test 1))", note1.getPattern());
		assertEquals(line1, "test 1");
		assertEquals(note1.toDebugString(), "note|");

		NoteInline note2 = new NoteInline();
		WikiObjectInlineI.Util sub2 = new WikiObjectInlineI.Util();
		String line2 = sub2.matchSet("((test 1(())test))", note2.getPattern());
		assertEquals(line2, "test 1(())test");
		assertEquals(note2.toDebugString(), "note|");

	}
}
