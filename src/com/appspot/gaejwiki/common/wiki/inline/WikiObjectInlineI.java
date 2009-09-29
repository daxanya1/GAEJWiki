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
	 * ������ǉ�����B
	 * @param str �ǉ����镶��
	 */
	void addString(String str);
	
	/**
	 * inline��ǉ�����
	 * @param wikiobject �qinline
	 */
	void addChildInline(WikiObjectInlineI wikiobject);
	
	/**
	 * �e��ݒ肷��
	 * @param wikiobject �einline
	 */
	void setParent(WikiObjectInlineI wikiobject);
	
	/**
	 * ����inline���q���Ƃ��Ēǉ��ł��邩�ǂ����B
	 * @return ����inline���q���Ƃ��Ēǉ��ł���ꍇ��true
	 */
	boolean isAddChildInline();
	
	/**
	 * �������g��e�ɒǉ��ł��邩
	 * @return �������g��e�ɒǉ��ł���ꍇ��true
	 */
	boolean isAddToParent();
	
	/**
	 * Wiki�t�H�[�}�b�g�p�̕������Ԃ��B
	 * @return
	 */
	String toString();
	
	/**
	 * �e��Ԃ�
	 * @return �e��WikiObjectInlineI
	 */
	WikiObjectInlineI getParent();
	
	/**
	 * �������g���ǂ������`�F�b�N����
	 */
	static interface Checker {
		boolean isThis(String str);
	}
}
