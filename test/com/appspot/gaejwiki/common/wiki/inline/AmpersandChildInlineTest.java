package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.*;

import org.junit.Test;

public class AmpersandChildInlineTest {

	@Test
	public void tetWikiObjectBlockFactorySub() {
		AmpersandChildInline.Checker checker = new AmpersandChildInline.Checker();
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("&br;"), 4);
		assertEquals(checker.getMatchLength("&br"), 0);
		assertEquals(checker.getMatchLength("&online;"), 8);
		assertEquals(checker.getMatchLength("&version;"), 9);
		assertEquals(checker.getMatchLength("&t;"), 3);
		assertEquals(checker.getMatchLength("&page;"), 6);
		assertEquals(checker.getMatchLength("&fpage;"), 7);
		assertEquals(checker.getMatchLength("&date;"), 6);
		assertEquals(checker.getMatchLength("&time;"), 6);
		assertEquals(checker.getMatchLength("&now;"), 5);
		assertEquals(checker.getMatchLength("&_date;"), 7);
		assertEquals(checker.getMatchLength("&_time;"), 7);
		assertEquals(checker.getMatchLength("&_now;"), 6);
		assertEquals(checker.getMatchLength("&_now2;"), 0);
		assertEquals(checker.getMatchLength("&#now2;"), 0);
		assertEquals(checker.getMatchLength("a&now2;"), 0);
		assertEquals(checker.getMatchLength("&ref(test);"), 11);
		assertEquals(checker.getMatchLength("&ref(test)();"), 0);
		assertEquals(checker.getMatchLength("&ref(test);()"), 11);
		assertEquals(checker.getMatchLength("&ref()"), 0);
		assertEquals(checker.getMatchLength("&ref"), 0);
		assertEquals(checker.getMatchLength("&reftest)"), 0);
		assertEquals(checker.getMatchLength("&ref(test"), 0);
		assertEquals(checker.getMatchLength("&counter;"), 9);
		assertEquals(checker.getMatchLength("&counter(test);"), 15);
		assertEquals(checker.getMatchLength("&counter();(test)"), 0);
		assertEquals(checker.getMatchLength("&counter(1);(test)"), 12);
		assertEquals(checker.getMatchLength("&counter()(test);"), 0);
		assertEquals(checker.getMatchLength("&#x0a;"), 6);
		assertEquals(checker.getMatchLength("&#x0123456789abcdef;"), 20);
		assertEquals(checker.getMatchLength("&#0123456789;"), 13);
		assertEquals(checker.getMatchLength("&#0123456789a;"), 0);
		assertEquals(checker.getMatchLength("&#xg;"), 0);
		assertEquals(checker.getMatchLength("&1#xg;"), 0);
		assertEquals(checker.getMatchLength("&#x01"), 0);
	}

}
