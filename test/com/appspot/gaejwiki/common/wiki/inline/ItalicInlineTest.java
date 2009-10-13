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
		String line1 = sub1.matchSet("'''test 1'''", inline1.getPattern(), 1);
		assertEquals(line1, "test 1");
		assertEquals(inline1.toDebugString(), "italic|");

		ItalicInline inline2 = new ItalicInline();
		WikiObjectInlineI.Util sub2 = new WikiObjectInlineI.Util();
		String line2 = sub2.matchSet("'''test 1'''test'''", inline1.getPattern(), 1);
		assertEquals(line2, "test 1'''test");
		assertEquals(inline2.toDebugString(), "italic|");

	}
}
