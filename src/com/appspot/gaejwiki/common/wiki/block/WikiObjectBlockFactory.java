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
	 * �����񂩂画�f����wikiobjectblock��Ԃ�
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
		
		// �����Ȃ��Ȃ�A�p���O���t�Ƃ݂Ȃ�
		return new Sub().createWikiObjectBlockFromChecker(new ParagraphBlock.Checker());
	}
	
	static public class Sub {
	
		/**
		 * checker�̃N���X������A���N���X�iParagraphBlock.Checker��������AParagraphBlock�j�����擾���A�C���X�^���X���쐬���ĕԂ�
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
