package com.appspot.gaejwiki.common.wiki.block;

public interface WikiObjectBlockI {

	public static final char PARAGRAPH = '~';
	public static final char QUOTATION = '>';
	public static final char UNQUOTATION = '<';
	public static final char FORMATED = ' ';
	public static final char UNNUMBEREDLIST = '-';
	public static final char NUMBEREDLIST = '+';
	public static final char DEFINEDLIST = ':';
	public static final char HASH = '#';
	public static final char HEADLINE = '*';
	public static final char TABLE = '|';
	public static final char CSV = ',';
	public static final char COMMENTFIRST = '/';
	
	public static final String COMMENT = "//";
	public static final String HORIZON = "----";
	public static final char DEFINEDLIST_SECONDCHAR = '|';
	
	/**
	 * 行を追加する。（一行目も含む）
	 * @param str
	 */
	void addLine(String str);
	
	/**
	 * blockを追加する
	 * @param wikiobject 子block
	 */
	void addChildBlock(WikiObjectBlockI wikiobject);
	
	/**
	 * 親を設定する
	 * @param wikiobject 親block
	 */
	void setParent(WikiObjectBlockI wikiobject);
	
	/**
	 * 次のblockを子供として追加できるかどうか。
	 * @return 次のblockを子供として追加できる場合はtrue
	 */
	boolean isAddChildBlock();
	
	/**
	 * 自分自身を親に追加できるか
	 * @return 自分自身を親に追加できる場合はtrue
	 */
	boolean isAddToParent();
	
	/**
	 * 次の行を追加できるかどうか。
	 * @return 次の行を追加できる場合はtrue
	 */
	boolean isAddLine();
	
	/**
	 * 次の行を追加する際に同じブロックの場合に限るかどうか。
	 * @return 次の行を追加する際に同じブロックの場合に限る場合はtrue
	 */
	boolean isSameBlockAddLine();
	
	/**
	 * このブロックを処理した後、仕切りとするかどうか
	 * @return このブロックを処理した後、仕切りとする場合はtrue
	 */
	boolean isReset();
	
	/**
	 * Wikiフォーマット用の文字列を返す。
	 * @return
	 */
	String toString();
	
	/**
	 * Debug用文字列を返す。
	 * @return
	 */
	String toDebugString();
	
	/**
	 * 親を返す
	 * @return 親のWikiObjectBlockI
	 */
	WikiObjectBlockI getParent();
	
	/**
	 * 自分自身かどうかをチェックする
	 */
	static interface Checker {
		boolean isThis(String line);
	}
}
