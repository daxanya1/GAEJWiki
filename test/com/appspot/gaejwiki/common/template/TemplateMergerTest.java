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

package com.appspot.gaejwiki.common.template;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 *
 * @author Ryuichi Danno
 */
public class TemplateMergerTest {

	/**
	 * @return
	 */
	private TemplateReplaceMap getReplaceMap1() {
		TemplateReplaceMap map = new TemplateReplaceMap();
		map.put("test", "a");
		map.put("<test>", "b");
		map.put("test\n", "c");
		return map;
	}

	
	@Test
	public void testSubMargeMap1a() {
		TemplateMerger.Sub sub = new TemplateMerger.Sub();
		String str = sub.margeMap("test<test>test\n", null, getReplaceMap1());
		assertEquals(str, "test<test>test\n");
	}

	@Test
	public void testSubMargeMap1b() {
		TemplateMerger.Sub sub = new TemplateMerger.Sub();
		String str = sub.margeMap("test<test>test\n", null, null);
		assertEquals(str, "test<test>test\n");
	}

	@Test
	public void testSubMargeMap2() {
		TemplateMerger.Sub sub = new TemplateMerger.Sub();
		Map<String, String> map = new HashMap<String, String>();
		map.put("test", null);
		String str = sub.margeMap("[test]<[test]>[test]\n", map, getReplaceMap1());
		assertEquals(str, "a<a>a\n");
	}

	@Test
	public void testSubMargeMap3() {
		TemplateMerger.Sub sub = new TemplateMerger.Sub();
		Map<String, String> map = new HashMap<String, String>();
		String str = sub.margeMap("[test][<test>][test]\n", map, getReplaceMap1());
		assertEquals(str, "[test][<test>][test]\n");
	}

	@Test
	public void testSubMargeMap4() {
		TemplateMerger.Sub sub = new TemplateMerger.Sub();
		Map<String, String> map = new HashMap<String, String>();
		map.put("<test>", null);
		String str = sub.margeMap("[test][<test>][test]\n", map, getReplaceMap1());
		assertEquals(str, "[test]b[test]\n");
	}

	@Test
	public void testSubMargeMap5() {
		TemplateMerger.Sub sub = new TemplateMerger.Sub();
		Map<String, String> map = new HashMap<String, String>();
		map.put("test\n", null);
		String str = sub.margeMap("[test][<test>][test]\n", map, getReplaceMap1());
		assertEquals(str, "[test][<test>][test]\n");
	}

	@Test
	public void testSubMargeMap6() {
		TemplateMerger.Sub sub = new TemplateMerger.Sub();
		Map<String, String> map = new HashMap<String, String>();
		map.put("test\n", null);
		map.put("test", null);
		String str = sub.margeMap("[test][<test>][test]\n", map, getReplaceMap1());
		assertEquals(str, "a[<test>]a\n");
	}


}
