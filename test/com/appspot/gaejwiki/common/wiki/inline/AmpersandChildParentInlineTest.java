package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.*;

import org.junit.Test;

public class AmpersandChildParentInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
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
		assertEquals(checker.getMatchLength("&size(1);"), 9);
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
	
	@Test
	public void testAmpersandChildParentInlineSub1() {
		AmpersandChildParentInline inline1 = new AmpersandChildParentInline();
		AmpersandChildParentInline.Sub sub1 = inline1.new Sub();
		String line1 = sub1.matchSet("&color(a){1};");
		assertEquals(line1, "1");
		assertEquals(inline1.toDebugString(), "color|a|");
		
		AmpersandChildParentInline inline2 = new AmpersandChildParentInline();
		AmpersandChildParentInline.Sub sub2 = inline2.new Sub();
		String line2 = sub2.matchSet("&ruby(aa){234};");
		assertEquals(line2, "234");
		assertEquals(inline2.toDebugString(), "ruby|aa|");
	}

	@Test
	public void testAmpersandChildParentInlineSub2() {
		AmpersandChildParentInline inline1 = new AmpersandChildParentInline();
		AmpersandChildParentInline.Sub sub1 = inline1.new Sub();
		String line1 = sub1.matchSet("&size(01){123};");
		assertEquals(line1, "123");
		assertEquals(inline1.toDebugString(), "size|01|");
		
		AmpersandChildParentInline inline2 = new AmpersandChildParentInline();
		AmpersandChildParentInline.Sub sub2 = inline2.new Sub();
		String line2 = sub2.matchSet("&size(012);");
		assertEquals(line2, null);
		assertEquals(inline2.toDebugString(), "size|012|");
	}

	@Test
	public void testAmpersandChildParentInlineSub3() {
		AmpersandChildParentInline inline1 = new AmpersandChildParentInline();
		AmpersandChildParentInline.Sub sub1 = inline1.new Sub();
		String line1 = sub1.matchSet("&aname(abcdefg){1234};");
		assertEquals(line1, "1234");
		assertEquals(inline1.toDebugString(), "aname|abcdefg|");
		
		AmpersandChildParentInline inline2 = new AmpersandChildParentInline();
		AmpersandChildParentInline.Sub sub2 = inline2.new Sub();
		String line2 = sub2.matchSet("&aname(abc09-_Z);");
		assertEquals(line2, null);
		assertEquals(inline2.toDebugString(), "aname|abc09-_Z|");
	}

}
