package com.appspot.gaejwiki.common.wiki.inline;

public interface WikiObjectInlineI {

	/**
	 * 文字を追加する。
	 * @param str 追加する文字
	 */
	void addString(String str);
	
	/**
	 * inlineを追加する
	 * @param wikiobject 子inline
	 */
	void addChildBlock(WikiObjectInlineI wikiobject);
	
	/**
	 * 親を設定する
	 * @param wikiobject 親inline
	 */
	void setParent(WikiObjectInlineI wikiobject);
	
	/**
	 * 次のinlineを子供として追加できるかどうか。
	 * @return 次のinlineを子供として追加できる場合はtrue
	 */
	boolean isAddChildBlock();
	
	/**
	 * 自分自身を親に追加できるか
	 * @return 自分自身を親に追加できる場合はtrue
	 */
	boolean isAddToParent();
	
	/**
	 * Wikiフォーマット用の文字列を返す。
	 * @return
	 */
	String toString();
	
	/**
	 * 親を返す
	 * @return 親のWikiObjectInlineI
	 */
	WikiObjectInlineI getParent();
}
