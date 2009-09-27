package com.appspot.gaejwiki.common.wiki;

import java.util.HashMap;

public class WikiObjectBlockFactory {

	public static final char PARAGRAPH = '~';
	public static final char QUOTATION = '>';
	public static final char UNQUOTATION = '<';
	public static final char FORMATED = ' ';
	public static final char UNNUMBEREDLIST = '-';
	public static final char NUMBEREDLIST = '+';
	public static final char LISTDEFINED = ':';
	public static final char CONTENTS = '#';
	public static final char HEADLINE = '*';
	public static final char TABLE = '|';
	public static final char CSV = ',';
	public static final char COMMENTFIRST = '/';
	
	public static final String COMMENT = "//";
	
	
	public WikiObjectI createWikiObject(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		
		BlockCheck blockcheck = new BlockCheck();
		
		if (blockcheck.isParagraph(line)) {
			return new ParagraphBlock();
		}
		
		if (blockcheck.isFormated(line)) {
			return new FormatedBlock();
		}
		
		if (blockcheck.isHeadline(line)) {
			return new HeadlineBlock();
		}
		
		if (blockcheck.isQuotation(line)) {
			return new QuotationBlock();
		}
		
		return null;
	}
	
	
	
	static public class BlockCheck {
		
		// 行頭書式の文字(-、+、:、>、|、#、//)
		private static final char[] NOTPARAGRAPHCHAR = { QUOTATION, UNQUOTATION, FORMATED, UNNUMBEREDLIST, NUMBEREDLIST, LISTDEFINED, CONTENTS, HEADLINE, TABLE, CSV, COMMENTFIRST };
		private static final HashMap<Character, Boolean> NOTPARAGRAPHS = new HashMap<Character, Boolean>();
		
		static {
			for (char notpara : NOTPARAGRAPHCHAR) {
				NOTPARAGRAPHS.put(new Character(notpara), Boolean.TRUE);
			}
		}
		
		/**
		 * パラグラフのblockかどうかチェックする
		 * ~が先頭であれば、パラグラフ
		 * 他のブロック要素であればパラグラフではない
		 * それ以外はパラグラフとする
		 * @param line　一行分の文字列
		 * @return パラグラフであればtrue
		 */
		public boolean isParagraph(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// PARAGRAPH要素が一文字目であれば、パラグラフとする
			if (line.charAt(0) == PARAGRAPH) {
				return true;
			}
			
			// 他のブロック要素であればパラグラフではない
			if (NOTPARAGRAPHS.containsKey(new Character(line.charAt(0)))) {
				if (line.charAt(0) != COMMENTFIRST || line.length() <= 1) {
					return false;
				} else {
					// コメントだけ特別扱い
					String checkstr = line.substring(0, 2);
					if (COMMENT.equals(checkstr)) {
						return false;
					}
				}
			}
			
			// それ以外はパラグラフとする。
			return true;
		}
		
		/**
		 * 整形済み文字列かどうかチェックする
		 * FORMATED要素が一文字目であれば、整形済み文字列とする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 整形済み文字列であればtrue
		 */
		public boolean isFormated(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// FORMATED要素が一文字目であれば、整形済み文字列とする
			return (line.charAt(0) == FORMATED) ? true : false;
		}
		
		
		/**
		 * 見出しどうかチェックする
		 * HEADLINE要素が一文字目であれば、見出しとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 見出しであればtrue
		 */
		public boolean isHeadline(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// HEADLINE要素が一文字目であれば、見出しとする
			return (line.charAt(0) == HEADLINE) ? true : false;
		}
		
		
		/**
		 * 引用どうかチェックする
		 * QUOTATION要素かUNQUOTATION要素が一文字目であれば、引用とする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 引用であればtrue
		 */
		public boolean isQuotation(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// QUOTATION要素かUNQUOTATION要素が一文字目であれば、引用とする
			return (line.charAt(0) == QUOTATION || line.charAt(0) == UNQUOTATION) ? true : false;
		}
		
	}
}
