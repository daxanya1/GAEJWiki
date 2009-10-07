package com.appspot.gaejwiki.common.wiki;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiBlockParser;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockFactory;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectBlockInfo;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

public class WikiParser {

	// private static final Logger logger = Logger.getLogger(WikiParser.class.getName());
	private WikiInlineParser inlineparser = null;

	
	public String parse(String str) {
		if (str == null) {
			return null;
		}
		
		String[] strlist = str.split(DomainParameter.getDomainParameter().getLineSeparator());
		List<String> linelist = java.util.Arrays.asList(strlist);
		
		WikiObjectBlockFactory blockfactory = new WikiObjectBlockFactory();
		WikiBlockParser blockparser = new WikiBlockParser();
		List<WikiObjectBlockI> blocklist = blockparser.parseBlock(blockfactory, linelist);
		
		
		WikiObjectInlineFactory inlinefactory = new WikiObjectInlineFactory();
		WikiObjectBlockInfo info = new WikiObjectBlockInfo();
		
		// inlineparser�͐V�K�쐬����(note�p��񂪕K�v�Ȃ��߁A�����o�ϐ��Ƃ��Ă���j
		inlineparser = new WikiInlineParser();
		inlineparser.setWikiObjectBlockInfo(info);
		inlineparser.setWikiObjectInlineFactory(inlinefactory);
		
		StringBuffer sb = new StringBuffer();
		for (WikiObjectBlockI block : blocklist) {
			block.paserInline(inlineparser);
			sb.append(block.toHtmlString());
		}
		
		return sb.toString();
	}
	
	/**
	 * note����HTML�`���ŕԂ�
	 * @return
	 */
	public String toNoteHtmlString() {
		return inlineparser.toNoteHtmlString();
	}
	
	
}
