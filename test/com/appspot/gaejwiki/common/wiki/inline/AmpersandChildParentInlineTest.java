package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.*;

import org.junit.Test;

public class AmpersandChildParentInlineTest {

	@Test
	public void tetWikiObjectBlockFactorySub() {
		AmpersandChildParentInline.Checker checker = new AmpersandChildParentInline.Checker();
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("&color;"), 0);
		assertEquals(checker.getMatchLength("&color();"), 0);
		assertEquals(checker.getMatchLength("&color(1);"), 0);
		assertEquals(checker.getMatchLength("&color(1){};"), 0);
		assertEquals(checker.getMatchLength("&color(1){1};"), 13);
		assertEquals(checker.getMatchLength("&color(a){1};"), 13);
		assertEquals(checker.getMatchLength("&size;"), 0);
		assertEquals(checker.getMatchLength("&size(a){1};"), 0);
		assertEquals(checker.getMatchLength("&size(1){1};"), 12);
		assertEquals(checker.getMatchLength("&ruby;"), 0);
		assertEquals(checker.getMatchLength("&ruby(1){1};"), 12);
		assertEquals(checker.getMatchLength("&aname;"), 0);
		assertEquals(checker.getMatchLength("&aname();"), 0);
		assertEquals(checker.getMatchLength("&aname(1);"), 0);
		assertEquals(checker.getMatchLength("&aname(a);"), 10);
		assertEquals(checker.getMatchLength("&aname(1){1};"), 0);
		assertEquals(checker.getMatchLength("&aname(a){1};"), 13);
		assertEquals(checker.getMatchLength("&aname(abc09-_Z){1};"), 20);
		assertEquals(checker.getMatchLength("&aname(abc-*){1};"), 0);
		assertEquals(checker.getMatchLength("&aname(()){1};"), 0);
	}

}
