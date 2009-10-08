package com.appspot.gaejwiki.domain.urlparam;

import static org.junit.Assert.*;

import java.util.regex.Matcher;

import org.junit.Test;

public class ParamParserTest {

	@Test
	public void testParamParserUtil1() {
		ParamParser.Util util = new ParamParser.Util();
		Matcher matcher = util.matchUrl("/view/aaa");
		assertNotNull(matcher);
		assertEquals(matcher.groupCount(), 2);
		String a2 = matcher.group(2);
		String a1 = matcher.group(1);
		assertEquals(matcher.group(1), "view/");
		assertEquals(matcher.group(2), "aaa");
	}
	
	@Test
	public void testParamParserUtil2() {
		ParamParser.Util util = new ParamParser.Util();
		Matcher matcher = util.matchUrl("/test/view2/aaab");
		assertNotNull(matcher);
		assertEquals(matcher.groupCount(), 2);
		assertEquals(matcher.group(1), "view2/");
		assertEquals(matcher.group(2), "aaab");
	}
	
	@Test
	public void testParamParserUtil3() {
		ParamParser.Util util = new ParamParser.Util();
		Matcher matcher = util.matchUrl("/test/test2/view3/aaac");
		assertNotNull(matcher);
		assertEquals(matcher.groupCount(), 2);
		assertEquals(matcher.group(1), "view3/");
		assertEquals(matcher.group(2), "aaac");
	}
	
	@Test
	public void testParamParserUtil4() {
		ParamParser.Util util = new ParamParser.Util();
		String page = util.getPage("/test/test2/view3/aaac", "view3");
		assertNotNull(page);
		assertEquals(page, "aaac");
	}

	@Test
	public void testParamParserUtil5() {
		ParamParser.Util util = new ParamParser.Util();
		String page = util.getPage("/test/test2/view3/aaac", "view");
		assertNull(page);
	}

}
