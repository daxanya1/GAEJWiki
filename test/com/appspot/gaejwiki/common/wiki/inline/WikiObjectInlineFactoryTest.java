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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory.WikiObjectInlineIPair;

public class WikiObjectInlineFactoryTest {

	public List<String> prepareInlineCheckLineList() {
		List<String> list = new ArrayList<String>();
		
		// 0 - 4
		list.add("&br;");
		list.add("&version;");
		list.add("&ref(test);");
		list.add("&counter(today);");
		list.add("&color(1){1};");
		// 5 - 9
		list.add("&size(1);");
		list.add("&aname(a);");
		list.add("test");
		list.add("'''test'''a");
		list.add("[[あああ>test:test#test]]");
		// 10 - 14
		list.add("[[test>hoge+test@aaa.com]]");
		list.add("~");
		list.add("((a () ))) ");
		list.add("[[pa：ge]]");
		list.add("[[page]]test");
		// 15 - 17
		list.add("%%1%% %% ");
		list.add("'' ' ''");
		list.add("WiWiWWiiiNAAAame2");
		list.add("[:[pa：ge]]");
		list.add("&test(a);");

		
		return list;
	}

	
	@Test
	public void tetWikiObjectBlockFactorySub() {
		
		WikiObjectInlineFactory.Sub sub = new WikiObjectInlineFactory.Sub();
		WikiObjectInlineI block = sub.createWikiObjectInlineFromChecker(new CharacterInline.Checker());
		assertEquals(block.getClass().getName(), CharacterInline.class.getName());
	}
	
	
	@Test
	public void testCreateWikiObjectBlock() {
		WikiObjectInlineFactory factory = new WikiObjectInlineFactory();
		List<String> list = prepareInlineCheckLineList();
		List<WikiObjectInlineIPair> blocklist = new ArrayList<WikiObjectInlineIPair>();
		for (String line : list) {
			blocklist.add(factory.createWikiObjectInline(line));
		}
		
		int count = 0;
		// 0-4
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), AmpersandChildInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), AmpersandChildInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), AmpersandChildInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), AmpersandChildInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), AmpersandChildParentInline.class.getName());
		// 5-9
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), AmpersandChildParentInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), AmpersandChildParentInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), CharacterInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), ItalicInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), LinkInline.class.getName());
		// 10-14
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), LinkInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), NewlineInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), NoteInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), PageInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), PageInline.class.getName());
		// 15-17
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), StrikeInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), StrongInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), WikiNameInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), CharacterInline.class.getName());
		assertEquals(blocklist.get(count++).getInline().getClass().getName(), CharacterInline.class.getName());
	}
}
