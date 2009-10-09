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
package com.appspot.gaejwiki.common.xml;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.fb.xml.parser.BitXmlParser;

import com.appspot.gaejwiki.common.template.TemplateData;

public class PtXmlParserTest extends TestCase {

	public PtXmlParserTest(String name) {
		super(name);
	}
	
	/**
	 * １項目、タグを<a></a>の形式
	 */
	public void testPtXmlParser1() {
		String xml1="<pt><phtml name=\"header\"><param key=\"title\"></param></phtml></pt>";
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( xml1 );
		TemplateData list = parser.getTemplateData();
		assertEquals(list.size(), 1);
		PtParam param = list.get(0);
		assertEquals(param.getName(), "header");
		Map<String, String> map = param.getMap();
		assertEquals(map.get("title"), "_");
	}
	
	/**
	 * １項目、タグを<a />の形式
	 */
	public void testPtXmlParser2() {
		String xml1="<pt><phtml name=\"header\"><param key=\"title\" /\"></phtml></pt>";
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( xml1 );
		TemplateData list = parser.getTemplateData();
		assertEquals(list.size(), 1);
		PtParam param = list.get(0);
		assertEquals(param.getName(), "header");
		Map<String, String> map = param.getMap();
		assertEquals(map.get("title"), "_");
	}

	/**
	 * ３項目、タグを<a></a>の形式
	 */
	public void testPtXmlParser3() {
		String xml1="<pt><phtml name=\"header\"><param key=\"title\"></param></phtml><phtml name=\"wikidata\"><param key=\"wiki\"></param></phtml><phtml name=\"footer\"></phtml></pt>";
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( xml1 );
		TemplateData list = parser.getTemplateData();
		assertEquals(list.size(), 3);
		PtParam param1 = list.get(0);
		assertEquals(param1.getName(), "header");
		Map<String, String> map1 = param1.getMap();
		assertEquals(map1.get("title"), "_");
		PtParam param2 = list.get(1);
		assertEquals(param2.getName(), "wikidata");
		Map<String, String> map2 = param2.getMap();
		assertNull(map2.get("title"));
		assertEquals(map2.get("wiki"), "_");
	}

	/**
	 * ３項目、タグを<a />の形式
	 */
	public void testPtXmlParser4() {
		String xml1="<pt><phtml name=\"header\"><param key=\"title\" /></phtml><phtml name=\"wikidata\"><param key=\"wiki\" /></phtml><phtml name=\"footer\" /></pt>";
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( xml1 );
		TemplateData list = parser.getTemplateData();
		assertEquals(list.size(), 3);
		PtParam param1 = list.get(0);
		assertEquals(param1.getName(), "header");
		Map<String, String> map1 = param1.getMap();
		assertEquals(map1.get("title"), "_");
		PtParam param2 = list.get(1);
		assertEquals(param2.getName(), "wikidata");
		Map<String, String> map2 = param2.getMap();
		assertNull(map2.get("title"));
		assertEquals(map2.get("wiki"), "_");
	}

	/**
	 * ３項目、keyを複数
	 */
	public void testPtXmlParser5() {
		String xml1="<pt><phtml name=\"header\"><param key=\"title\" /></phtml><phtml name=\"wikidata\"><param key=\"wiki\" /><param key=\"wiki2\" /></phtml><phtml name=\"footer\" /></pt>";
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( xml1 );
		TemplateData list = parser.getTemplateData();
		assertEquals(list.size(), 3);
		PtParam param1 = list.get(0);
		assertEquals(param1.getName(), "header");
		Map<String, String> map1 = param1.getMap();
		assertEquals(map1.get("title"), "_");
		PtParam param2 = list.get(1);
		assertEquals(param2.getName(), "wikidata");
		Map<String, String> map2 = param2.getMap();
		assertEquals(map2.get("wiki"), "_");
		assertEquals(map2.get("wiki2"), "_");
		PtParam param3 = list.get(2);
		assertEquals(param3.getName(), "footer");
		Map<String, String> map3 = param3.getMap();
		assertNull(map3.get("title"));
	}

	/**
	 * 空　parseSaxでNullPointException
	 */
	public void testPtXmlParser6() {
		String xml1=null;
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		try {
			xParser1.parseSax( xml1 );
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * 空文字　parseSaxでStringIndexOutOfBoundsException
	 */
	public void testPtXmlParser7() {
		String xml1="";
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		try {
			xParser1.parseSax( xml1 );
			assertTrue(false);
		} catch (StringIndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	/**
	 * 1文字　リストが空
	 */
	public void testPtXmlParser8() {
		String xml1="1";
		BitXmlParser xParser1 = new BitXmlParser();
		PtXmlParser parser = new PtXmlParser();
		xParser1.setListener( parser );
		xParser1.parseSax( xml1 );
		TemplateData list = parser.getTemplateData();
		assertEquals(list.size(), 0);
	}

	/**
	 * ダブルクォート除去チェック　空
	 */
	public void testTrimDoubleQuote1() {
		String param = null;
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertNull(param2);
	}

	/**
	 * ダブルクォート除去チェック　空文字
	 */
	public void testTrimDoubleQuote2() {
		String param = "";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "");
	}

	/**
	 * ダブルクォート除去チェック　片方"
	 */
	public void testTrimDoubleQuote3() {
		String param = "test\"";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "test\"");
	}

	/**
	 * ダブルクォート除去チェック　真ん中"
	 */
	public void testTrimDoubleQuote4() {
		String param = "test\"test";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "test\"test");
	}

	/**
	 * ダブルクォート除去チェック　端と真ん中"
	 */
	public void testTrimDoubleQuote5() {
		String param = "\"test\"test\"";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "test\"test");
	}

}
