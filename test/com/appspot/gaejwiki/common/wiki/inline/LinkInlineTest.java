/**
 Copyright 2009 GAEJWiki Team.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.appspot.gaejwiki.common.wiki.inline;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkInlineTest {

	
	@Test
	public void tetWikiObjectInlineChecker1() {
		LinkInline.Checker checker = new LinkInline.Checker();
		assertEquals(checker.getMatchLength("[[test:http://hoge.jp/]]"), 24);
		assertEquals(checker.getMatchLength("[[あああ>http://hoge.jp/]]"), 23);
		assertEquals(checker.getMatchLength("[[test>hoge+test@aaa.com]]"), 26);
		assertEquals(checker.getMatchLength("[[test:hoge+test@aaa.com]]"), 26);
		assertEquals(checker.getMatchLength("[[http://hoge.jp/]]"), 19);
		assertEquals(checker.getMatchLength("[[hoge+test@aaa.com]]"), 21);
		assertEquals(checker.getMatchLength("[[あああ>]]"), 0);
		assertEquals(checker.getMatchLength("[[あああ>test]]"), 12);
		assertEquals(checker.getMatchLength("[[あああ>test:test#test]]"), 22);
		assertEquals(checker.getMatchLength("[[ああ あ:test]]"), 13);
		assertEquals(checker.getMatchLength("[[あああ:te:st]]"), 0);
	}

	
	@Test
	public void tetWikiObjectInlineChecker() {
		LinkInline.Checker checker = new LinkInline.Checker();
		
		assertEquals(checker.getMatchLength("[[page]]"), 0);
		assertEquals(checker.getMatchLength("[[page]]test"), 0);
		assertEquals(checker.getMatchLength("[[pa#ge]]"), 0);
		assertEquals(checker.getMatchLength("[[pa：ge]]"), 0);
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
		assertEquals(checker.getMatchLength("[[t::test]]"), 0);
		assertEquals(checker.getMatchLength("[[t:http://test]]"), 17);
		assertEquals(checker.getMatchLength("[[test>#http://test]]"), 21);
	}

	
	@Test
	public void testLinkInlineSub1() {
		LinkInline link1 = new LinkInline();
		link1.set("[[test:http://www.hoge.jp/]]", null);
		assertEquals(link1.toDebugString(), "true|test||http://www.hoge.jp/|");
	}
	
	@Test
	public void testLinkInlineSub2() {
		LinkInline link1 = new LinkInline();
		link1.set("[[test:hoge+test@aaa.com]]", null);
		assertEquals(link1.toDebugString(), "true|test||hoge+test@aaa.com|");
	}
	
	@Test
	public void testLinkInlineSub3() {
		LinkInline link1 = new LinkInline();
		link1.set("[[http://www.hoge.jp/]]", null);
		assertEquals(link1.toDebugString(), "true|||http://www.hoge.jp/|");
	}
	
	@Test
	public void testLinkInlineSub4() {
		LinkInline link1 = new LinkInline();
		link1.set("[[hoge+test@aaa.com]]", null);
		assertEquals(link1.toDebugString(), "true|||hoge+test@aaa.com|");
	}
	
	@Test
	public void testLinkInlineSub5() {
		LinkInline link1 = new LinkInline();
		link1.set("[[あああ>test:test#test]]", null);
		assertEquals(link1.toDebugString(), "false|あああ|test|test#test|");

		LinkInline link2 = new LinkInline();
		link2.set("[[あああ>test#test]]", null);
		assertEquals(link2.toDebugString(), "false|あああ||test#test|");
	}

	@Test
	public void testLinkInlineSub6() {
		LinkInline link1 = new LinkInline();
		link1.set("[[test:test#test]]", null);
		assertEquals(link1.toDebugString(), "false||test|test#test|");
	}

}
