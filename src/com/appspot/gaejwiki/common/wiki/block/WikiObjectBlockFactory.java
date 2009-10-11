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
package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class WikiObjectBlockFactory {

	static final Map<Character, List<WikiObjectBlockI.Checker>> blockmap;
	
	static {
		blockmap = new HashMap<Character, List<WikiObjectBlockI.Checker>>();
		blockmap.put(getC(WikiObjectBlockI.PARAGRAPH), getL(new ParagraphBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.QUOTATION), getL(new QuotationBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.UNQUOTATION), getL(new QuotationBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.FORMATED), getL(new FormatedBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.UNNUMBEREDLIST), getL2(new UnnumberedListBlock.Checker(), new HorizonBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.NUMBEREDLIST), getL(new NumberedListBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.DEFINEDLIST), getL(new DefinedListBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.CENTER), getL(new AlignBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.LEFT), getL(new AlignBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.RIGHT), getL(new AlignBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.HASH), getL(new HashBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.HEADLINE), getL(new HeadlineBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.TABLE), getL(new TableBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.CSV), getL(new CsvBlock.Checker()));
		blockmap.put(getC(WikiObjectBlockI.COMMENTFIRST), getL(new CommentBlock.Checker()));
	}
	
	static public Character getC(char c) {
		return new Character(c);
	}
	
	static public List<WikiObjectBlockI.Checker> getL(WikiObjectBlockI.Checker checker) {
		List<WikiObjectBlockI.Checker> list = new ArrayList<WikiObjectBlockI.Checker>();
		list.add(checker);
		return list;
	}
	
	static public List<WikiObjectBlockI.Checker> getL2(WikiObjectBlockI.Checker checker1, WikiObjectBlockI.Checker checker2) {
		List<WikiObjectBlockI.Checker> list = new ArrayList<WikiObjectBlockI.Checker>();
		list.add(checker1);
		list.add(checker2);
		return list;
	}
	
	/**
	 * 文字列から判断してwikiobjectblockを返す
	 * @param line
	 * @return
	 */
	public WikiObjectBlockI createWikiObjectBlock(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		
		Character checkc = new Character(line.charAt(0));
		List<WikiObjectBlockI.Checker> listc = blockmap.get(checkc);
		
		if (listc != null) {
			for (WikiObjectBlockI.Checker checker : listc) {
				if (checker.isThis(line)) {
					return new Sub().createWikiObjectBlockFromChecker(checker);
				}
			}
		}
		
		// 何もないなら、パラグラフとみなす
		return new Sub().createWikiObjectBlockFromChecker(new ParagraphBlock.Checker());
	}
	
	static public class Sub {
	
		/**
		 * checkerのクラス名から、元クラス（ParagraphBlock.Checkerだったら、ParagraphBlock）名を取得し、インスタンスを作成して返す
		 * @param classname
		 * @return WikiObjectBlock
		 */
		protected WikiObjectBlockI createWikiObjectBlockFromChecker(WikiObjectBlockI.Checker checker) {
			try {
				String name = checker.getClass().getName();
				String classname = name.substring(0, name.length() - ".Checker".length());
				Class<?> entity = Class.forName(classname);
				return (WikiObjectBlockI)entity.newInstance();
			} catch (Exception e) {
				assert(false);
				return null;
			}
		}
	}
	
	
}
