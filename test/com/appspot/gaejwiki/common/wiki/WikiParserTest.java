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
package com.appspot.gaejwiki.common.wiki;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.domain.setting.DomainParameter;


public class WikiParserTest {

	public WikiParser getTestWikiParser() {
		return new WikiParser() {
			protected WikiInlineParser getInlineParser() {
				return new WikiInlineParser() {
					public boolean checkPage(String pagename) {
						return false;
					}					
				}; 
			}
		};
	}
	
	@Before
	public void initDomainParameter() {
		DomainParameter domainparam = DomainParameter.getDomainParameter();
		domainparam.putUnitTestOnly(DomainParameter.DOMAINURL, "gaejwiki.appspot.com");
		domainparam.putUnitTestOnly(DomainParameter.VIEWURL, "view/");
		domainparam.putUnitTestOnly(DomainParameter.VIEWTEMPLATE, "/template/view.pt");
		domainparam.putUnitTestOnly(DomainParameter.EDITURL, "edit/");
		domainparam.putUnitTestOnly(DomainParameter.EDITTEMPLATE, "/template/edit.pt");
		domainparam.putUnitTestOnly(DomainParameter.LINESEPARATOR, "\n");
	}
	
	@Test
	public void tetWikiParser1() {
		// block:段落,整形済み
		// inline:文字のみ
		
		String str = "test1\n test2\ntest3\n test4\n test5\ntest6";
		String resultstr = "<p>test1</p>\n<pre>test2</pre>\n<p>test3</p>\n<pre>test4\ntest5</pre>\n<p>test6</p>\n";
		
		WikiParser parser = getTestWikiParser();
		String result = parser.parse("reftest", str);
		assertEquals(result, resultstr);
	}

	@Test
	public void tetWikiParser2() {
		// block:段落,align,コメント
		// inline:文字,italic,strong
		
		String str = "test1\nCENTER:test2''test3'''test4'''''\n//test5\ntest6";
		String resultstr = "<p>test1</p>\n<div style=\"text-align:center\">test2<strong>test3<em>test4</em></strong>\ntest6</div>\n";
		
		WikiParser parser = getTestWikiParser();
		String result = parser.parse("reftest", str);
		assertEquals(result, resultstr);
	}

	@Test
	public void tetWikiParser3() {
		// block:段落,Hash(hr),Horizon
		// inline:文字,strike,note,newline
		
		String str = "test1\ntest2~\n#hr\n-----test3\ntest4%%test5%%((test6''test7''))\n(('''test8''test9'''''))";
		String resultstr = "<p>test1\ntest2<br /></p>\n<hr class=\"short_line\" />\n<hr class=\"full_hr\" />\n<p>test4<del>test5</del><a id=\"notetext_1\" href=\"#notefoot_1\" class=\"note_super\" title=\"test6test7\">*1</a>\n<a id=\"notetext_2\" href=\"#notefoot_2\" class=\"note_super\" title=\"test8test9\">*2</a></p>\n";
		String notestr = "<div id=\"note\"><hr class=\"note_hr\" /><a id=\"notefoot_1\" href=\"#notetext_1\" class=\"note_super\">*1</a>\n<span class=\"small\">test6<strong>test7</strong></span><br />\n<a id=\"notefoot_2\" href=\"#notetext_2\" class=\"note_super\">*2</a>\n<span class=\"small\"><em>test8<strong>test9</strong></em></span><br /></div>";
		WikiParser parser = getTestWikiParser();
		String result = parser.parse("reftest", str);
		String noteresult = parser.toNoteHtmlString();
		assertEquals(result, resultstr);
		assertEquals(noteresult, notestr);
	}


	@Test
	public void tetWikiParser4() {
		// block:段落,Hash(br),Headline
		// inline:文字,Page
		
		String str = "test1\n*test2%%test3%%[[test4]]&br~\ntest5\n*test6\ntest7\n**test8\ntest9\n***test10\ntest11\n#br\ntest12[[test13]]";
		String resultstr = "<p>test1</p>\n<h2 id=\"content_1_0\">test2<del>test3</del><span class=\"noexists\">test4<a href=\"/edit/test4?ref=reftest\">?</a></span>&amp;br~  <a class=\"anchor_super\" id=\"id0\" href=\"/view/reftest#id0\" title=\"id0\">&dagger;</a></h2>\n<p>test5</p>\n<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div><h2 id=\"content_1_1\">test6  <a class=\"anchor_super\" id=\"id1\" href=\"/view/reftest#id1\" title=\"id1\">&dagger;</a></h2>\n<p>test7</p>\n<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div><h3 id=\"content_1_2\">test8  <a class=\"anchor_super\" id=\"id2\" href=\"/view/reftest#id2\" title=\"id2\">&dagger;</a></h3>\n<p>test9</p>\n<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div><h4 id=\"content_1_3\">test10  <a class=\"anchor_super\" id=\"id3\" href=\"/view/reftest#id3\" title=\"id3\">&dagger;</a></h4>\n<p>test11</p>\n<div class=\"spacer\">&nbsp;</div>\n<p>test12<span class=\"noexists\">test13<a href=\"/edit/test13?ref=reftest\">?</a></span></p>\n";
		WikiParser parser = getTestWikiParser();
		String result = parser.parse("reftest", str);
		assertEquals(result, resultstr);

	}

	@Test
	public void tetWikiParser5() {
		// block:段落,csv
		// inline:文字
		
		String str = ",test1,test2,test3\n, test4 ,==, test5";
		String resultstr = "<div class=\"ie5\"><table class=\"style_table\" cellspacing=\"1\" border=\"0\">\n<tr class=\"style_tr\"><td class=\"style_td\">test1</td><td class=\"style_td\">test2</td><td class=\"style_td\">test3</td></tr>\n<tr class=\"style_tr\"><td class=\"style_td\" style=\"text-align:center\" colspan=\"2\">test4</td><td class=\"style_td\" style=\"text-align:right\">test5</td></tr>\n</table></div>\n";
		WikiParser parser = getTestWikiParser();
		String result = parser.parse("reftest", str);
		assertEquals(result, resultstr);

	}

	@Test
	public void tetWikiParser6() {
		// block:段落,csv
		// inline:文字
		
		String str = ",test1,test2,test3\n, test4 ,==, test5,";
		String resultstr = "<div class=\"ie5\"><table class=\"style_table\" cellspacing=\"1\" border=\"0\">\n<tr class=\"style_tr\"><td class=\"style_td\">test1</td><td class=\"style_td\">test2</td><td class=\"style_td\">test3</td></tr>\n</table></div>\n<div class=\"ie5\"><table class=\"style_table\" cellspacing=\"1\" border=\"0\">\n<tr class=\"style_tr\"><td class=\"style_td\" style=\"text-align:center\" colspan=\"2\">test4</td><td class=\"style_td\" style=\"text-align:right\">test5</td><td class=\"style_td\"></td></tr>\n</table></div>\n";
		WikiParser parser = getTestWikiParser();
		String result = parser.parse("reftest", str);
		assertEquals(result, resultstr);

	}
	
	@Test
	public void tetWikiParser7() {
		// block:段落,csv
		// inline:文字,strong,italic,strike,note,newline
		
		String str = ",test1,'''''test2''''',%%test3%%\n, test6((test4)) ,==, '''test5'''";
		String resultstr = "<div class=\"ie5\"><table class=\"style_table\" cellspacing=\"1\" border=\"0\">\n<tr class=\"style_tr\"><td class=\"style_td\">test1</td><td class=\"style_td\"><em><strong>test2</strong></em></td><td class=\"style_td\"><del>test3</del></td></tr>\n<tr class=\"style_tr\"><td class=\"style_td\" style=\"text-align:center\" colspan=\"2\">test6<a id=\"notetext_1\" href=\"#notefoot_1\" class=\"note_super\" title=\"test4\">*1</a></td><td class=\"style_td\" style=\"text-align:right\"><em>test5</em></td></tr>\n</table></div>\n";
		WikiParser parser = getTestWikiParser();
		String result = parser.parse("reftest", str);
		assertEquals(result, resultstr);
		String notestr = "<div id=\"note\"><hr class=\"note_hr\" /><a id=\"notefoot_1\" href=\"#notetext_1\" class=\"note_super\">*1</a>\n<span class=\"small\">test4</span><br /></div>";
		String noteresult = parser.toNoteHtmlString();
		assertEquals(noteresult, notestr);

	}
}
