package com.appspot.gaejwiki.common.wiki.inline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class WikiObjectInlineFactory {

	static final Map<Character, List<WikiObjectInlineI.Checker>> blockmap;
	
	static {
		blockmap = new HashMap<Character, List<WikiObjectInlineI.Checker>>();
		blockmap.put(getC(WikiObjectInlineI.ANGLEBRACKET), getL2(new PageInline.Checker(), new LinkInline.Checker()));
	}
	
	static public Character getC(char c) {
		return new Character(c);
	}
	
	static public List<WikiObjectInlineI.Checker> getL(WikiObjectInlineI.Checker checker) {
		List<WikiObjectInlineI.Checker> list = new ArrayList<WikiObjectInlineI.Checker>();
		list.add(checker);
		return list;
	}
	
	static public List<WikiObjectInlineI.Checker> getL2(WikiObjectInlineI.Checker checker1, WikiObjectInlineI.Checker checker2) {
		List<WikiObjectInlineI.Checker> list = new ArrayList<WikiObjectInlineI.Checker>();
		list.add(checker1);
		list.add(checker2);
		return list;
	}
	
	/**
	 * 文字列から判断してwikiobjectblockを返す
	 * @param line
	 * @return
	 */
	public WikiObjectInlineI createWikiObjectBlock(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		
		Character checkc = new Character(line.charAt(0));
		List<WikiObjectInlineI.Checker> listc = blockmap.get(checkc);
		
		// 何もない場合はparagraphチェックして、終わり
		if (listc == null) {
//			return (new ParagraphBlock.Checker().isThis(line)) ? new Sub().createWikiObjectInlineFromChecker(new ParagraphBlock.Checker()) : null;
		}
		
		for (WikiObjectInlineI.Checker checker : listc) {
			if (checker.isThis(line)) {
				return new Sub().createWikiObjectInlineFromChecker(checker);
			}
		}
		
		// 何もなければnullを返す
		return null;
	}
	
	static public class Sub {
	
		/**
		 * checkerのクラス名から、元クラス（ParagraphBlock.Checkerだったら、ParagraphBlock）名を取得し、インスタンスを作成して返す
		 * @param classname
		 * @return WikiObjectBlock
		 */
		protected WikiObjectInlineI createWikiObjectInlineFromChecker(WikiObjectInlineI.Checker checker) {
			try {
				String name = checker.getClass().getName();
				String classname = name.substring(0, name.length() - ".Checker".length());
				Class<?> entity = Class.forName(classname);
				return (WikiObjectInlineI)entity.newInstance();
			} catch (Exception e) {
				assert(false);
				return null;
			}
		}
	}
	
	
}
