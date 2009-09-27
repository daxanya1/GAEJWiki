package com.appspot.gaejwiki.common.wiki;

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
