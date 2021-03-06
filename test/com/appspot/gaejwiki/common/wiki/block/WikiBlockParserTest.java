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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class WikiBlockParserTest {

	public WikiObjectBlockFactory getTestFactory() {
		return new WikiObjectBlockFactory();
	}
	
	
	public List<String> prepareWikiTestList1() {
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("");
		list.add("test2");
		list.add("test3");
		
		return list;
	}
	
	@Test
	public void testWikiBlockParser1() {
		WikiBlockParser parser = new WikiBlockParser();
		
		List<WikiObjectBlockI> wikilist = parser.parseBlock(getTestFactory(), prepareWikiTestList1());
		assertEquals(wikilist.size(), 2);
		assertEquals(((WikiObjectBlockI)wikilist.get(0)).toDebugString(), "test1\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(1)).toDebugString(), "test2\ntest3\n");
	}
	
	public List<String> prepareWikiTestList2() {
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("");
		list.add(" test2");
		list.add(" test3");
		list.add("test4");
		
		return list;
	}
	
	@Test
	public void testWikiBlockParser2() {
		WikiBlockParser parser = new WikiBlockParser();
		
		List<WikiObjectBlockI> wikilist = parser.parseBlock(getTestFactory(), prepareWikiTestList2());
		assertEquals(wikilist.size(), 3);
		assertEquals(((WikiObjectBlockI)wikilist.get(0)).toDebugString(), "test1\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(1)).toDebugString(), " test2\n test3\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(2)).toDebugString(), "test4\n");
	}
	
	public List<String> prepareWikiTestList3() {
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add("*test2");
		list.add("test3");
		list.add(" test4");
		list.add("test5");
		list.add("test6");
		
		return list;
	}
	
	@Test
	public void testWikiBlockParser3() {
		WikiBlockParser parser = new WikiBlockParser();
		
		List<WikiObjectBlockI> wikilist = parser.parseBlock(getTestFactory(), prepareWikiTestList3());
		assertEquals(wikilist.size(), 5);
		assertEquals(((WikiObjectBlockI)wikilist.get(0)).toDebugString(), "test1\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(1)).toDebugString(), "*test2\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(2)).toDebugString(), "test3\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(3)).toDebugString(), " test4\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(4)).toDebugString(), "test5\ntest6\n");
	}
	
	public List<String> prepareWikiTestList4() {
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add(">test2");
		list.add(" test3");
		list.add(" test4");
		list.add("test5");
		list.add("< test6");
		list.add("test7");
		list.add("test8");
		
		return list;
	}
	
	@Test
	public void testWikiBlockParser4() {
		WikiBlockParser parser = new WikiBlockParser();
		
		List<WikiObjectBlockI> wikilist = parser.parseBlock(getTestFactory(), prepareWikiTestList4());
		assertEquals(wikilist.size(), 2);
		assertEquals(((WikiObjectBlockI)wikilist.get(0)).toDebugString(), "test1\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(1)).toDebugString(), ">test2\n/c: test3\n test4\n:c//c:test5\n:c//c:< test6\ntest7\ntest8\n:c/");
	}
	
	public List<String> prepareWikiTestList5() {
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add(">test2");
		list.add(" test3");
		list.add(" test4");
		list.add("*test5");
		list.add("< test6");
		list.add("test7");
		list.add("test8");
		list.add("#hr");
		list.add("#br");
		
		return list;
	}
	
	@Test
	public void testWikiBlockParser5() {
		WikiBlockParser parser = new WikiBlockParser();
		
		List<WikiObjectBlockI> wikilist = parser.parseBlock(getTestFactory(), prepareWikiTestList5());
		// for (WikiObjectI i : wikilist) {
		// 	System.out.print(i.toString() + "\n\n");
		// }
		assertEquals(wikilist.size(), 4);
		assertEquals(((WikiObjectBlockI)wikilist.get(0)).toDebugString(), "test1\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(1)).toDebugString(), ">test2\n/c: test3\n test4\n:c/");
		assertEquals(((WikiObjectBlockI)wikilist.get(2)).toDebugString(), "*test5\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(3)).toDebugString(), "< test6\ntest7\ntest8\n/c:#hr\n:c//c:#br\n:c/");
	}
	
	public List<String> prepareWikiTestList6() {
		List<String> list = new ArrayList<String>();
		list.add("test1");
		list.add(">test2");
		list.add(" test3");
		list.add(" test4");
		list.add("*test5");
		list.add(" test6");
		list.add("test7");
		list.add("test8");
		list.add("#br");
		list.add("//test");
		list.add("#hr");
		
		return list;
	}
	
	@Test
	public void testWikiBlockParser6() {
		WikiBlockParser parser = new WikiBlockParser();
		
		List<WikiObjectBlockI> wikilist = parser.parseBlock(getTestFactory(), prepareWikiTestList6());
		// for (WikiObjectI i : wikilist) {
		// 	System.out.print(i.toString() + "\n\n");
		// }
		assertEquals(wikilist.size(), 7);
		assertEquals(((WikiObjectBlockI)wikilist.get(0)).toDebugString(), "test1\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(1)).toDebugString(), ">test2\n/c: test3\n test4\n:c/");
		assertEquals(((WikiObjectBlockI)wikilist.get(2)).toDebugString(), "*test5\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(3)).toDebugString(), " test6\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(4)).toDebugString(), "test7\ntest8\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(5)).toDebugString(), "#br\n");
		assertEquals(((WikiObjectBlockI)wikilist.get(6)).toDebugString(), "#hr\n");
	}
	
	
}
