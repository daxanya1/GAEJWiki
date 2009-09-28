package com.appspot.gaejwiki.common.wiki.base;

public interface WikiObjectI {

	/**
	 * �s��ǉ�����B�i��s�ڂ��܂ށj
	 * @param str
	 */
	void addLine(String str);
	
	/**
	 * block��ǉ�����
	 * @param wikiobject �qblock
	 */
	void addChildBlock(WikiObjectI wikiobject);
	
	/**
	 * �e��ǉ�����
	 * @param wikiobject �eblock
	 */
	void setParent(WikiObjectI wikiobject);
	
	/**
	 * ����block���q���Ƃ��Ēǉ��ł��邩�ǂ����B
	 * @return ����block���q���Ƃ��Ēǉ��ł���ꍇ��true
	 */
	boolean isAddChildBlock();
	
	/**
	 * �������g��e�ɒǉ��ł��邩
	 * @return �������g��e�ɒǉ��ł���ꍇ��true
	 */
	boolean isAddToParent();
	
	/**
	 * ���̍s��ǉ��ł��邩�ǂ����B
	 * @return ���̍s��ǉ��ł���ꍇ��true
	 */
	boolean isAddLine();
	
	/**
	 * ���̍s��ǉ�����ۂɓ����u���b�N�̏ꍇ�Ɍ��邩�ǂ����B
	 * @return ���̍s��ǉ�����ۂɓ����u���b�N�̏ꍇ�Ɍ���ꍇ��true
	 */
	boolean isSameBlockAddLine();
	
	/**
	 * ���̃u���b�N������������A�d�؂�Ƃ��邩�ǂ���
	 * @return ���̃u���b�N������������A�d�؂�Ƃ���ꍇ��true
	 */
	boolean isReset();
	
	/**
	 * Wiki�t�H�[�}�b�g�p�̕������Ԃ��B
	 * @return
	 */
	String toString();
	
	/**
	 * �e��Ԃ�
	 * @return �e��WikiObjectI
	 */
	WikiObjectI getParent();
}
