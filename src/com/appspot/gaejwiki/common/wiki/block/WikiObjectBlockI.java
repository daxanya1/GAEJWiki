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
	 * �s��ǉ�����B�i��s�ڂ��܂ށj
	 * @param str
	 */
	void addLine(String str);
	
	/**
	 * block��ǉ�����
	 * @param wikiobject �qblock
	 */
	void addChildBlock(WikiObjectBlockI wikiobject);
	
	/**
	 * �e��ݒ肷��
	 * @param wikiobject �eblock
	 */
	void setParent(WikiObjectBlockI wikiobject);
	
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
	 * Debug�p�������Ԃ��B
	 * @return
	 */
	String toDebugString();
	
	/**
	 * �e��Ԃ�
	 * @return �e��WikiObjectBlockI
	 */
	WikiObjectBlockI getParent();
	
	/**
	 * �������g���ǂ������`�F�b�N����
	 */
	static interface Checker {
		boolean isThis(String line);
	}
}
