package com.appspot.gaejwiki.common.wiki.base;

public interface WikiObjectI {

	/**
	 * 行を追加する。（一行目も含む）
	 * @param str
	 */
	void addLine(String str);
	
	/**
	 * blockを追加する
	 * @param wikiobject 子block
	 */
	void addChildBlock(WikiObjectI wikiobject);
	
	/**
	 * 親を追加する
	 * @param wikiobject 親block
	 */
	void setParent(WikiObjectI wikiobject);
	
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
	 * 親を返す
	 * @return 親のWikiObjectI
	 */
	WikiObjectI getParent();
}
