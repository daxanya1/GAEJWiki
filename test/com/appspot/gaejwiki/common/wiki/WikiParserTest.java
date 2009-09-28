package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WikiParserTest {

	public WikiObjectBlockFactory getTestFactory() {
		return new WikiObjectBlockFactory() {
			
			private String toRawlistString(List<String> rawlist) {
				StringBuffer sb = new StringBuffer();
				for (String str : rawlist) {
					sb.append(str);
					sb.append("\n");
				}
				return sb.toString();
			}
			
			private String toChildlistString(List<WikiObjectI> childlist) {
				StringBuffer sb = new StringBuffer();
				for (WikiObjectI wikiobj : childlist) {
					sb.append("/c:");
					sb.append(wikiobj.toString());
					sb.append(":c/");
				}
				return sb.toString();
			}
			
			protected WikiObjectI createParagraphBlock() {
				return new ParagraphBlock() {
					public String toString() {
						return toRawlistString(getRawlist());
					}
				};
			}
			
			protected WikiObjectI createFormatedBlock() {
				return new FormatedBlock() {
					public String toString() {
						return toRawlistString(getRawlist());
					}
				};
			}
			
			protected WikiObjectI createHeadlineBlock() {
				return new HeadlineBlock() {
					public String toString() {
						return getData() + "\n";
					}
				};
			}
			
			protected WikiObjectI createQuotationBlock() {
				return new QuotationBlock() {
					public String toString() {
						return toRawlistString(getRawlist()) + toChildlistString(getChildlist());
					}
				};
			}			
		};
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
	public void testWikiParser1() {
		WikiParser parser = new WikiParser();
		
		List<WikiObjectI> wikilist = parser.parse(getTestFactory(), prepareWikiTestList1());
		assertEquals(wikilist.size(), 2);
		assertEquals(((WikiObjectI)wikilist.get(0)).toString(), "test1\n");
		assertEquals(((WikiObjectI)wikilist.get(1)).toString(), "test2\ntest3\n");
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
	public void testWikiParser2() {
		WikiParser parser = new WikiParser();
		
		List<WikiObjectI> wikilist = parser.parse(getTestFactory(), prepareWikiTestList2());
		assertEquals(wikilist.size(), 3);
		assertEquals(((WikiObjectI)wikilist.get(0)).toString(), "test1\n");
		assertEquals(((WikiObjectI)wikilist.get(1)).toString(), " test2\n test3\n");
		assertEquals(((WikiObjectI)wikilist.get(2)).toString(), "test4\n");
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
	public void testWikiParser3() {
		WikiParser parser = new WikiParser();
		
		List<WikiObjectI> wikilist = parser.parse(getTestFactory(), prepareWikiTestList3());
		assertEquals(wikilist.size(), 5);
		assertEquals(((WikiObjectI)wikilist.get(0)).toString(), "test1\n");
		assertEquals(((WikiObjectI)wikilist.get(1)).toString(), "*test2\n");
		assertEquals(((WikiObjectI)wikilist.get(2)).toString(), "test3\n");
		assertEquals(((WikiObjectI)wikilist.get(3)).toString(), " test4\n");
		assertEquals(((WikiObjectI)wikilist.get(4)).toString(), "test5\ntest6\n");
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
	public void testWikiParser4() {
		WikiParser parser = new WikiParser();
		
		List<WikiObjectI> wikilist = parser.parse(getTestFactory(), prepareWikiTestList4());
		assertEquals(wikilist.size(), 2);
		assertEquals(((WikiObjectI)wikilist.get(0)).toString(), "test1\n");
		assertEquals(((WikiObjectI)wikilist.get(1)).toString(), ">test2\n/c: test3\n test4\n:c//c:test5\n:c//c:< test6\ntest7\ntest8\n:c/");
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
		
		return list;
	}
	
	@Test
	public void testWikiParser5() {
		WikiParser parser = new WikiParser();
		
		List<WikiObjectI> wikilist = parser.parse(getTestFactory(), prepareWikiTestList5());
		// for (WikiObjectI i : wikilist) {
		// 	System.out.print(i.toString() + "\n\n");
		// }
		assertEquals(wikilist.size(), 4);
		assertEquals(((WikiObjectI)wikilist.get(0)).toString(), "test1\n");
		assertEquals(((WikiObjectI)wikilist.get(1)).toString(), ">test2\n/c: test3\n test4\n:c/");
		assertEquals(((WikiObjectI)wikilist.get(2)).toString(), "*test5\n");
		assertEquals(((WikiObjectI)wikilist.get(3)).toString(), "< test6\ntest7\ntest8\n");
	}
	
	
}
