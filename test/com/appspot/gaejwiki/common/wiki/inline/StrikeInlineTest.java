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

public class StrikeInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		StrikeInline.Checker checker = new StrikeInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("%%test%%"), 8);
		assertEquals(checker.getMatchLength("%%%%"), 0);
		assertEquals(checker.getMatchLength("%%1%% "), 5);
		assertEquals(checker.getMatchLength(" %%1%% "), 0);
		assertEquals(checker.getMatchLength("%%1%% %% "), 8);

	}

	@Test
	public void testStrikeInlineSub() {
		StrikeInline strike1 = new StrikeInline();
		WikiObjectInlineI.Util sub1 = new WikiObjectInlineI.Util();
		String line1 = sub1.matchSet("%%test 1%%", strike1.getPattern());
		assertEquals(line1, "test 1");
		assertEquals(strike1.toDebugString(), "strike|");

		StrikeInline strike2 = new StrikeInline();
		WikiObjectInlineI.Util sub2 = new WikiObjectInlineI.Util();
		String line2 = sub2.matchSet("%%test 1%%test%%", strike2.getPattern());
		assertEquals(line2, "test 1%%test");
		assertEquals(strike2.toDebugString(), "strike|");

	}
}
