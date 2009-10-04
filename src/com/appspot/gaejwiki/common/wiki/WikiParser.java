package com.appspot.gaejwiki.common.wiki;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiBlockParser;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockFactory;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectBlockInfo;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;

public class WikiParser {

	// private static final Logger logger = Logger.getLogger(WikiParser.class.getName());

	
	public String parse(String str) {
		if (str == null) {
			return null;
		}
		
		String[] strlist = str.split(System.getProperty("line.separator"));
		List<String> linelist = java.util.Arrays.asList(strlist);
		
		WikiObjectBlockFactory blockfactory = new WikiObjectBlockFactory();
		WikiBlockParser blockparser = new WikiBlockParser();
		List<WikiObjectBlockI> blocklist = blockparser.parseBlock(blockfactory, linelist);
		
		
		WikiObjectInlineFactory inlinefactory = new WikiObjectInlineFactory();
		WikiInlineParser inlineparser = new WikiInlineParser();
		WikiObjectBlockInfo info = new WikiObjectBlockInfo();
		
		inlineparser.setWikiObjectBlockInfo(info);
		inlineparser.setWikiObjectInlineFactory(inlinefactory);
		
		StringBuffer sb = new StringBuffer();
		for (WikiObjectBlockI block : blocklist) {
			block.paserInline(inlineparser);
			sb.append(block.toString());
		}
		
		return sb.toString();
	}
	
	
}
