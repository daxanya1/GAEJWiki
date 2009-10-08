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
package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WikiObjectBlockFactoryTest {

	@Test
	public void tetWikiObjectBlockFactorySub() {
		
		WikiObjectBlockFactory.Sub sub = new WikiObjectBlockFactory.Sub();
		WikiObjectBlockI block = sub.createWikiObjectBlockFromChecker(new ParagraphBlock.Checker());
		assertEquals(block.getClass().getName(), ParagraphBlock.class.getName());
	}
	
	
	@Test
	public void testCreateWikiObjectBlock() {
		WikiObjectBlockFactory factory = new WikiObjectBlockFactory();
		List<String> list = new WikiObjectBlockCheckTest().prepareBlockCheckLineList();
		List<WikiObjectBlockI> blocklist = new ArrayList<WikiObjectBlockI>();
		for (String line : list) {
			blocklist.add(factory.createWikiObjectBlock(line));
		}
		
		int count = 0;
		// 0-4
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), QuotationBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), QuotationBlock.class.getName());
		// 5-9
		assertEquals(blocklist.get(count++).getClass().getName(), QuotationBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), QuotationBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), QuotationBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), QuotationBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), FormatedBlock.class.getName());
		// 10-14
		assertEquals(blocklist.get(count++).getClass().getName(), FormatedBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HeadlineBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), NumberedListBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), NumberedListBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), NumberedListBlock.class.getName());
		// 15-19
		assertEquals(blocklist.get(count++).getClass().getName(), UnnumberedListBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), UnnumberedListBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), UnnumberedListBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), CommentBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		// 20-24
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), TableBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), CsvBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		// 25-29
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertNull(blocklist.get(count++));
		assertNull(blocklist.get(count++));
		assertEquals(blocklist.get(count++).getClass().getName(), DefinedListBlock.class.getName());
		// 30-34
		assertEquals(blocklist.get(count++).getClass().getName(), DefinedListBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HorizonBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HorizonBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), AlignBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), AlignBlock.class.getName());
		// 35-39
		assertEquals(blocklist.get(count++).getClass().getName(), AlignBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), AlignBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		// 40-44
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		// 45-49
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), HashBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
		assertEquals(blocklist.get(count++).getClass().getName(), ParagraphBlock.class.getName());
	}
}
