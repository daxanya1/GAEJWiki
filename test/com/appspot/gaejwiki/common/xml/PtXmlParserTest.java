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
	 * �P���ځA�^�O��<a></a>�̌`��
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
	 * �P���ځA�^�O��<a />�̌`��
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
	 * �R���ځA�^�O��<a></a>�̌`��
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
	 * �R���ځA�^�O��<a />�̌`��
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
	 * �R���ځAkey�𕡐�
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
	 * ��@parseSax��NullPointException
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
	 * �󕶎��@parseSax��StringIndexOutOfBoundsException
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
	 * 1�����@���X�g����
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
	 * �_�u���N�H�[�g�����`�F�b�N�@��
	 */
	public void testTrimDoubleQuote1() {
		String param = null;
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertNull(param2);
	}

	/**
	 * �_�u���N�H�[�g�����`�F�b�N�@�󕶎�
	 */
	public void testTrimDoubleQuote2() {
		String param = "";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "");
	}

	/**
	 * �_�u���N�H�[�g�����`�F�b�N�@�Е�"
	 */
	public void testTrimDoubleQuote3() {
		String param = "test\"";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "test\"");
	}

	/**
	 * �_�u���N�H�[�g�����`�F�b�N�@�^��"
	 */
	public void testTrimDoubleQuote4() {
		String param = "test\"test";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "test\"test");
	}

	/**
	 * �_�u���N�H�[�g�����`�F�b�N�@�[�Ɛ^��"
	 */
	public void testTrimDoubleQuote5() {
		String param = "\"test\"test\"";
		PtXmlParser.Util util = new PtXmlParser.Util();
		String param2 = util.trimDoubleQuote(param);
		assertEquals(param2, "test\"test");
	}

}
