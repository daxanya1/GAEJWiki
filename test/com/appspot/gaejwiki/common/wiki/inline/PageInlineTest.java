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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PageInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		PageInline.Checker checker = new PageInline.Checker();
		
		assertEquals(checker.getMatchLength("[[page]]"), 8);
		assertEquals(checker.getMatchLength("[[page]]test"), 8);
		assertEquals(checker.getMatchLength("[[pa#ge]]"), 9);
		assertEquals(checker.getMatchLength("[[pa：ge]]"), 9);
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("test[[page]]test"), 0);
		assertEquals(checker.getMatchLength("[[pa:ge]]"), 0);
		assertEquals(checker.getMatchLength("[[page]:]"), 0);
		assertEquals(checker.getMatchLength("[:[page]]"), 0);
		assertEquals(checker.getMatchLength("[[page>]]"), 0);
		assertEquals(checker.getMatchLength("[[page&]]"), 0);
		assertEquals(checker.getMatchLength("[[\"page]]"), 0);
		assertEquals(checker.getMatchLength("[[<page]]"), 0);
	}

	@Test
	public void testPageInlineSub() {
		PageInline page1 = new PageInline();
		page1.set("[[test 1]]", null);
		assertEquals(page1.toDebugString(), "page|test 1");

		PageInline page2 = new PageInline();
		page2.set("[[test 1[[]]test]]", null);
		assertEquals(page2.toDebugString(), "page|test 1[[");

	}
}
