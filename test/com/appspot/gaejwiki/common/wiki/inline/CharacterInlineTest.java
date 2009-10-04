package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CharacterInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		CharacterInline.Checker checker = new CharacterInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 1);
		assertEquals(checker.getMatchLength(null), 1);

	}

	@Test
	public void testCharacterInlineSub() {
		CharacterInline inline1 = new CharacterInline();
		inline1.set("test", null);
		assertEquals(inline1.toDebugString(), "test");
	}
}
