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

import java.util.List;
import java.util.Set;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.common.wiki.block.WikiBlockParser;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockFactory;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectBlockInfo;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;
import com.appspot.gaejwiki.data.dao.WikiRef;

/**
 *
 * @author Ryuichi Danno
 */
public class WikiParser {

	// private static final Logger logger = Logger.getLogger(WikiParser.class.getName());
	private WikiObjectBlockInfo info = null;

	public WikiParser() {
		info = getSub().getWikiObjectBlockInfo();
		info.setAllPageData(getSub().getAllPageData());
	}
	
	public String parse(String pagename, String str) {
		if (str == null) {
			return null;
		}
		
		List<String> linelist = new TextUtils().parseDataToList(str);
		
		WikiObjectBlockFactory blockfactory = new WikiObjectBlockFactory();
		WikiBlockParser blockparser = new WikiBlockParser();
		List<WikiObjectBlockI> blocklist = blockparser.parseBlock(blockfactory, linelist);
		
		
		WikiObjectInlineFactory inlinefactory = new WikiObjectInlineFactory();
		
		// inlineparserは新規作成する(note用情報が必要なため、メンバ変数としている）
		WikiInlineParser inlineparser = getSub().getInlineParser();
		info.setAccessPagename(pagename);
		inlineparser.setWikiObjectBlockInfo(info);
		inlineparser.setWikiObjectInlineFactory(inlinefactory);
		
		StringBuffer sb = new StringBuffer();
		for (WikiObjectBlockI block : blocklist) {
			block.paserInline(inlineparser);
			sb.append(block.toHtmlString());
		}
		
		return sb.toString();
	}
	
	public Sub getSub() {
		return new Sub();
	}
	
	/**
	 * note情報をHTML形式で返す
	 * @return
	 */
	public String toNoteHtmlString() {
		return info.toNoteHtmlString();
	}
	
	public Set<String> getNonExistsPageSet() {
		return info.getNonExistsPageSet();
	}
	
	static public class Sub {

		public WikiInlineParser getInlineParser() {
			return new WikiInlineParser(); 
		}
		
		public WikiObjectBlockInfo getWikiObjectBlockInfo() {
			return new WikiObjectBlockInfo();
		}

		/**
		 * @return
		 */
		public String getAllPageData() {
			WikiRef.Util refutil = new WikiRef.Util();
			WikiRef ref = refutil.loadData(WikiRef.Util.ALLPAGEKEY);
			if (ref == null) {
				return new String("");
			}
			return ref.getRefdata();
		}
	}

}
