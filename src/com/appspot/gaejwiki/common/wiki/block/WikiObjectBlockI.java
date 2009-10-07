package com.appspot.gaejwiki.common.wiki.block;

import java.util.List;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

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
	public static final char CENTER = 'C';
	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	public static final char CSV = ',';
	public static final char COMMENTFIRST = '/';
	
	public static final String COMMENT = "//";
	public static final String HORIZON = "----";
	public static final char DEFINEDLIST_SECONDCHAR = '|';
	public static final String CENTERFORMAT = "CENTER:";
	public static final String LEFTFORMAT = "LEFT:";
	public static final String RIGHTFORMAT = "RIGHT:";
	public static final String HASHFORMATPATTERN1 = "^#(contents|hr|br|clear|comment|pcomment|article)$";
	public static final String HASHFORMATPATTERN2 = "^#(ref)\\((.+)\\)$";
	public static final String HASHFORMATPATTERN3 = "^#(vote)\\((.+)\\)$";

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
	 * �V���v���ȁi�^�O�̂��Ă��Ȃ��j�������Ԃ��B
	 * @return
	 */
	String toString();
	
	/**
	 * HTML�t�H�[�}�b�g�p�̕������Ԃ��B
	 * @return
	 */
	String toHtmlString();
	
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
	 * �u���b�N����������p�[�X����Inline�z��ɂ���B
	 * @param parser
	 */
	void paserInline(WikiInlineParser parser);
	
	/**
	 * ���x���̊T�O������u���b�N�ɂ��ă��x����Ԃ��B
	 * ���x���̊T�O���Ȃ��u���b�N�ɂ��Ă�-1��Ԃ�
	 * @return ���x����Ԃ��B
	 */
	int getLevel();
	
	
	static public class Util {
		
		/**
		 * �s�̐擪��؂��ĕԂ�
		 * @param data
		 * @return
		 */
		public String cutFrontChar(String data, int length) {
			if (data == null) {
				return null;
			}
			
			return data.substring(length, data.length());
		}
		
		/**
		 * ���̕������擪����A���łǂꂾ���܂܂�Ă��邩��Ԃ�
		 * ���x���͍ő�R�܂łȂ̂ŁA�R�ȏ�̏ꍇ�A�R��Ԃ�
		 * @param data ������
		 * @param c �`�F�b�N���镶��
		 * @return �擪��c���Ȃ����-1�A�擪��c������ΘA�����鐔��Ԃ�
		 */
		public int checkLevel(String data, char c) {
			if (data == null) {
				return -1;
			}
			
			if (data.length() == 0 || data.charAt(0) != c) {
				return -1;
			}
			
			int count = 1;
			for (int i = 1; i < 3; i++) {
				if (data.charAt(i) != c) {
					break;
				}
				count++;
			}
			return count;
		}
		
		/**
		 * inline��child��toHtmlString���Ăяo���āAStringBuffer�ɋl�߂ĕԂ�
		 * @param inlinelist WikiObjectInlineI�̃��X�g
		 * @param childlist WikiObjectBlockI�̃��X�g
		 * @return toHtmlString������
		 */
		public String toHtmlString(List<WikiObjectInlineI> inlinelist, List<WikiObjectBlockI> childlist) {
			StringBuffer sb = new StringBuffer();
			if (inlinelist != null) {
				for (WikiObjectInlineI inline : inlinelist) {
					sb.append(inline.toHtmlString());
				}
			}
			if (childlist != null) {
				for (WikiObjectBlockI wikiobj : childlist) {
					sb.append(wikiobj.toHtmlString());
				}
			}
			return sb.toString();
		}
		
		public String getLineSeparator() {
			return DomainParameter.getDomainParameter().getLineSeparator();
		}
	}


	static interface Checker {
		
		/**
		 * �����̃u���b�N�����ɍ����Ă��邩�ǂ������`�F�b�N����
		 * @param line �`�F�b�N�Ώە�����
		 * @return �����̃u���b�N�����ɂ����Ă����true
		 */
		boolean isThis(String line);
	}
}
