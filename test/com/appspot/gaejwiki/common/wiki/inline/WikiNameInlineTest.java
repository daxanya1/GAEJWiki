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

public class WikiNameInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		WikiNameInline.Checker checker = new WikiNameInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("WikiName"), 8);
		assertEquals(checker.getMatchLength("Wiki1Name"), 0);
		assertEquals(checker.getMatchLength("wWikiName"), 0);
		assertEquals(checker.getMatchLength("WWWWName"), 0);
		assertEquals(checker.getMatchLength("WWWWiiiNAAAame"), 14);
		assertEquals(checker.getMatchLength("WWWWiiiNAAAame2"), 14);
		assertEquals(checker.getMatchLength("WiWiWWiiiNAAAame2"), 4);

	}

	@Test
	public void testWikiNameInlineSub() {
		WikiNameInline inline1 = new WikiNameInline();
		inline1.set("WikiNNamee", null);
		assertEquals(inline1.toDebugString(), "wikiname|WikiNNamee|");
	}
}
