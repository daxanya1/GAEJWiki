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
