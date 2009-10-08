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

public class NewlineInlineTest {

	@Test
	public void tetWikiObjectInlineChecker() {
		NewlineInline.Checker checker = new NewlineInline.Checker();
		
		assertEquals(checker.getMatchLength(""), 0);
		assertEquals(checker.getMatchLength(null), 0);
		assertEquals(checker.getMatchLength("~"), 1);
		assertEquals(checker.getMatchLength("a~"), 0);
		assertEquals(checker.getMatchLength("~ "), 0);
	}

	@Test
	public void testNewlineInlineSub() {
		NewlineInline inline1 = new NewlineInline();
		inline1.set("~", null);
		assertEquals(inline1.toDebugString(), "<br />");
	}
}
