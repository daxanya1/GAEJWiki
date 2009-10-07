package com.appspot.gaejwiki.common.wiki;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import com.appspot.gaejwiki.domain.setting.DomainParameter;


public class WikiParserTest {

	public WikiParser getTestWikiParser() {
		return new WikiParser() {
		};
	}
	
	@Before
	public void initDomainParameter() {
		DomainParameter domainparam = DomainParameter.getDomainParameter();
		domainparam.putUnitTestOnly(DomainParameter.DOMAINURL, "gaejwiki.appspot.com");
		domainparam.putUnitTestOnly(DomainParameter.VIEWURL, "/view");
		domainparam.putUnitTestOnly(DomainParameter.VIEWTEMPLATE, "/template/view.pt");
		domainparam.putUnitTestOnly(DomainParameter.EDITURL, "/edit");
		domainparam.putUnitTestOnly(DomainParameter.EDITTEMPLATE, "/template/edit.pt");
		domainparam.putUnitTestOnly(DomainParameter.LINESEPARATOR, "\n");
	}
	
	@Test
	public void tetWikiParser1() {
		// block:�i��,���`�ς�
		// inline:�����̂�
		
		String str = "test1\n test2\ntest3\n test4\n test5\ntest6";
		String resultstr = "<p>test1</p>\n<pre>test2</pre>\n<p>test3</p>\n<pre>test4\ntest5</pre>\n<p>test6</p>\n";
		
		WikiParser parser = getTestWikiParser();
		String result = parser.parse(str);
		assertEquals(result, resultstr);
	}

	@Test
	public void tetWikiParser2() {
		// block:�i��,align,�R�����g
		// inline:����,italic,strong
		
		String str = "test1\nCENTER:test2''test3'''test4'''''\n//test5\ntest6";
		String resultstr = "<p>test1</p>\n<div style=\"text-align:center\">test2<strong>test3<em>test4</em></strong>\ntest6</div>\n";
		
		WikiParser parser = getTestWikiParser();
		String result = parser.parse(str);
		assertEquals(result, resultstr);
	}

	@Test
	public void tetWikiParser3() {
		// block:�i��,Hash(hr),Horizon
		// inline:����,strike,note,newline
		
		String str = "test1\ntest2~\n#hr\n-----test3\ntest4%%test5%%((test6''test7''))\n(('''test8''test9'''''))";
		String resultstr = "<p>test1\ntest2<br /></p>\n<hr class=\"short_line\" />\n<hr class=\"full_hr\" />\n<p>test4<del>test5</del><a id=\"notetext_1\" href=\"#notefoot_1\" class=\"note_super\" title=\"test6test7\">*1</a>\n<a id=\"notetext_2\" href=\"#notefoot_2\" class=\"note_super\" title=\"test8test9\">*2</a></p>\n";
		String notestr = "<div id=\"note\"><hr class=\"note_hr\" /><a id=\"notefoot_1\" href=\"#notetext_1\" class=\"note_super\">*1</a>\n<span class=\"small\">test6<strong>test7</strong></span><br />\n<a id=\"notefoot_2\" href=\"#notetext_2\" class=\"note_super\">*2</a>\n<span class=\"small\"><em>test8<strong>test9</strong></em></span><br /></div>";
		WikiParser parser = getTestWikiParser();
		String result = parser.parse(str);
		String noteresult = parser.toNoteHtmlString();
		assertEquals(result, resultstr);
		assertEquals(noteresult, notestr);
	}


	@Test
	public void tetWikiParser4() {
		// block:�i��,Hash(br),Headline
		// inline:����,Page
		
		String str = "test1\n*test2%%test3%%[[test4]]&br~\ntest5\n*test6\ntest7\n**test8\ntest9\n***test10\ntest11\n#br\ntest12[[test13]]";
		String resultstr = "<p>test1</p>\n<h2 id=\"content_1_0\">test2<del>test3</del><span class=\"noexists\">test4<a href=\"/edit?page=test4&amp;refer=ref\">?</a></span>&amp;br~  <a class=\"anchor_super\" id=\"id0\" href=\"/view?ref#id0\" title=\"id0\">&dagger;</a></h2>\n<p>test5</p>\n<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div><h2 id=\"content_1_1\">test6  <a class=\"anchor_super\" id=\"id1\" href=\"/view?ref#id1\" title=\"id1\">&dagger;</a></h2>\n<p>test7</p>\n<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div><h3 id=\"content_1_2\">test8  <a class=\"anchor_super\" id=\"id2\" href=\"/view?ref#id2\" title=\"id2\">&dagger;</a></h3>\n<p>test9</p>\n<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div><h4 id=\"content_1_3\">test10  <a class=\"anchor_super\" id=\"id3\" href=\"/view?ref#id3\" title=\"id3\">&dagger;</a></h4>\n<p>test11</p>\n<div class=\"spacer\">&nbsp;</div>\n<p>test12<span class=\"noexists\">test13<a href=\"/edit?page=test13&amp;refer=ref\">?</a></span></p>\n";
		WikiParser parser = getTestWikiParser();
		String result = parser.parse(str);
		String noteresult = parser.toNoteHtmlString();
		assertEquals(result, resultstr);

	}

}
