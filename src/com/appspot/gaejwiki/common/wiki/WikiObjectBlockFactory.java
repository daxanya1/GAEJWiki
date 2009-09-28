package com.appspot.gaejwiki.common.wiki;

import java.util.HashMap;

public class WikiObjectBlockFactory {

	public static final char PARAGRAPH = '~';
	public static final char QUOTATION = '>';
	public static final char UNQUOTATION = '<';
	public static final char FORMATED = ' ';
	public static final char UNNUMBEREDLIST = '-';
	public static final char NUMBEREDLIST = '+';
	public static final char DEFINEDLIST = ':';
	public static final char CONTENTS = '#';
	public static final char HEADLINE = '*';
	public static final char TABLE = '|';
	public static final char CSV = ',';
	public static final char COMMENTFIRST = '/';
	
	public static final String COMMENT = "//";
	public static final String HORIZON = "----";
	public static final char DEFINEDLIST_SECONDCHAR = '|';
	
	
	public WikiObjectI createWikiObject(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		
		BlockCheck blockcheck = new BlockCheck();
		
		if (blockcheck.isParagraph(line)) {
			return createParagraphBlock();
		}
		
		if (blockcheck.isFormated(line)) {
			return createFormatedBlock();
		}
		
		if (blockcheck.isHeadline(line)) {
			return createHeadlineBlock();
		}
		
		if (blockcheck.isQuotation(line)) {
			return createQuotationBlock();
		}
		
		// 
		return null;
	}
	
	protected WikiObjectI createParagraphBlock() {
		return new ParagraphBlock();
	}
	
	protected WikiObjectI createFormatedBlock() {
		return new FormatedBlock();
	}
	
	protected WikiObjectI createHeadlineBlock() {
		return new HeadlineBlock();
	}
	
	protected WikiObjectI createQuotationBlock() {
		return new QuotationBlock();
	}
	
	
	
	static public class BlockCheck {
		
		// 行頭書式の文字(-、+、:、>、|、#、//)
		private static final char[] NOTPARAGRAPHCHAR = { QUOTATION, UNQUOTATION, FORMATED, UNNUMBEREDLIST, NUMBEREDLIST, DEFINEDLIST, CONTENTS, HEADLINE, TABLE, CSV, COMMENTFIRST };
		private static final HashMap<Character, Boolean> NOTPARAGRAPHS = new HashMap<Character, Boolean>();
		
		static {
			for (char notpara : NOTPARAGRAPHCHAR) {
				NOTPARAGRAPHS.put(new Character(notpara), Boolean.TRUE);
			}
		}
		
		/**
		 * コメント行かどうか確認
		 * @param line
		 * @return コメント行ならtrue
		 */
		public boolean isComment(String line) {
			// ２文字以下ならコメントではない
			if (line == null || line.length() < 2) {
				return false;
			}
			
			// ２文字目まで調べて、//だったらtrue
			String checkstr = line.substring(0, 2);
			return (COMMENT.equals(checkstr)) ? true : false;
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
				if (line.charAt(0) != COMMENTFIRST && line.charAt(0) != DEFINEDLIST) {
					return false;
				} else {
					// コメントと定義リストだけ特別扱い
					if (line.charAt(0) != COMMENTFIRST) {
						// コメントかどうか確認
						if (isComment(line)) {
							return false;
						}
					}
					if (line.charAt(0) != DEFINEDLIST) {
						// 定義リストの場合 どこかに|があれば定義リスト、それ以外は段落扱い
						if (isDefinedList(line)) {
							return false;
						}
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
		
		/**
		 * CSVどうかチェックする
		 * CSV要素が一文字目であれば、CSVとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return CSVであればtrue
		 */
		public boolean isCsv(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// CSV要素が一文字目であれば、CSVとする
			return (line.charAt(0) == CSV) ? true : false;
		}
		
		/**
		 * 定義リストどうかチェックする
		 * 定義リスト要素が一文字目であれば、定義リストとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 定義リストであればtrue
		 */
		public boolean isDefinedList(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// 定義リスト要素が一文字目でかつ、どこかに|があれば定義リストとする
			return (line.charAt(0) == DEFINEDLIST && line.indexOf(DEFINEDLIST_SECONDCHAR) >= 0) ? true : false;
		}
		
		/**
		 * 水平線かどうかチェックする
		 * 水平線要素があれば、水平線とする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 水平線であればtrue
		 */
		public boolean isHorizon(String line) {
			if (line == null || line.length() < 4) {
				return false;
			}
			
			// ４文字目まで調べて、----だったらtrue
			String checkstr = line.substring(0, 4);
			return (HORIZON.equals(checkstr)) ? true : false;
		}

		/**
		 * リストどうかチェックする
		 * 先に水平線かどうかを調べる
		 * 水平線でなければ、NUMBEREDLIST要素かUNNUMBERED要素が一文字目であれば、リストとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return リストであればtrue
		 */
		public boolean isList(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// 水平線であれば違う
			if (isHorizon(line)) {
				return false;
			}
			
			// NUMBEREDLIST要素かUNNUMBEREDLIST要素が一文字目であれば、リストとする
			return (line.charAt(0) == NUMBEREDLIST || line.charAt(0) == UNNUMBEREDLIST) ? true : false;
		}
		

	}
}
