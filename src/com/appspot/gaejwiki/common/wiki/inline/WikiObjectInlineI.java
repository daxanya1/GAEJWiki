package com.appspot.gaejwiki.common.wiki.inline;

public interface WikiObjectInlineI {

	/**
	 * ������ǉ�����B
	 * @param str �ǉ����镶��
	 */
	void addString(String str);
	
	/**
	 * inline��ǉ�����
	 * @param wikiobject �qinline
	 */
	void addChildBlock(WikiObjectInlineI wikiobject);
	
	/**
	 * �e��ݒ肷��
	 * @param wikiobject �einline
	 */
	void setParent(WikiObjectInlineI wikiobject);
	
	/**
	 * ����inline���q���Ƃ��Ēǉ��ł��邩�ǂ����B
	 * @return ����inline���q���Ƃ��Ēǉ��ł���ꍇ��true
	 */
	boolean isAddChildBlock();
	
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
}
