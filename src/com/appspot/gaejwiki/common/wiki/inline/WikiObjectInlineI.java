package com.appspot.gaejwiki.common.wiki.inline;

import java.util.HashMap;

public interface WikiObjectInlineI {

	public static final char AMPERSAND = '&';
	public static final char STRONG = '\'';
	public static final char PARCENT = '%';
	public static final char ROUNDBRACKET = '(';
	public static final char ANGLEBRACKET = '[';
	
	public static final String PAGEFORMATBEGIN = "[[";
	public static final String PAGEFORMATEND = "]]";
	public static final char[] PAGENOTINCLUDES = { '"', ':', '&', '<', '>' };
	public static final char[] LINKINCLUDES = { ':', '>' };
	public static final char ANCHOR = '#';


	/**
	 * 文字を追加する。
	 * @param str 追加する文字
	 */
	void addString(String str);
	
	/**
	 * inlineを追加する
	 * @param wikiobject 子inline
	 */
	void addChildInline(WikiObjectInlineI wikiobject);
	
	/**
	 * 親を設定する
	 * @param wikiobject 親inline
	 */
	void setParent(WikiObjectInlineI wikiobject);
	
	/**
	 * 次のinlineを子供として追加できるかどうか。
	 * @return 次のinlineを子供として追加できる場合はtrue
	 */
	boolean isAddChildInline();
	
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
	
	/**
	 * 自分自身かどうかをチェックする
	 */
	static interface Checker {
		boolean isThis(String str);
	}
}
