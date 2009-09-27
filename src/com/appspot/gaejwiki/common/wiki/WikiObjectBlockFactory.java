package com.appspot.gaejwiki.common.wiki;

import java.util.HashMap;

public class WikiObjectBlockFactory {

	public static final char PARAGRAPH = '~';
	public static final char QUOTATION = '>';
	public static final char UNQUOTATION = '<';
	public static final char FORMATED = ' ';
	public static final char UNNUMBEREDLIST = '-';
	public static final char NUMBEREDLIST = '+';
	public static final char LISTDEFINED = ':';
	public static final char CONTENTS = '#';
	public static final char HEADLINE = '*';
	public static final char TABLE = '|';
	public static final char CSV = ',';
	public static final char COMMENTFIRST = '/';
	
	public static final String COMMENT = "//";
	
	
	public WikiObjectI createWikiObject(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		
		BlockCheck blockcheck = new BlockCheck();
		
		if (blockcheck.isParagraph(line)) {
			return new ParagraphBlock();
		}
		
		if (blockcheck.isFormated(line)) {
			return new FormatedBlock();
		}
		
		if (blockcheck.isHeadline(line)) {
			return new HeadlineBlock();
		}
		
		if (blockcheck.isQuotation(line)) {
			return new QuotationBlock();
		}
		
		return null;
	}
	
	
	
	static public class BlockCheck {
		
		// �s�������̕���(-�A+�A:�A>�A|�A#�A//)
		private static final char[] NOTPARAGRAPHCHAR = { QUOTATION, UNQUOTATION, FORMATED, UNNUMBEREDLIST, NUMBEREDLIST, LISTDEFINED, CONTENTS, HEADLINE, TABLE, CSV, COMMENTFIRST };
		private static final HashMap<Character, Boolean> NOTPARAGRAPHS = new HashMap<Character, Boolean>();
		
		static {
			for (char notpara : NOTPARAGRAPHCHAR) {
				NOTPARAGRAPHS.put(new Character(notpara), Boolean.TRUE);
			}
		}
		
		/**
		 * �p���O���t��block���ǂ����`�F�b�N����
		 * ~���擪�ł���΁A�p���O���t
		 * ���̃u���b�N�v�f�ł���΃p���O���t�ł͂Ȃ�
		 * ����ȊO�̓p���O���t�Ƃ���
		 * @param line�@��s���̕�����
		 * @return �p���O���t�ł����true
		 */
		public boolean isParagraph(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// PARAGRAPH�v�f���ꕶ���ڂł���΁A�p���O���t�Ƃ���
			if (line.charAt(0) == PARAGRAPH) {
				return true;
			}
			
			// ���̃u���b�N�v�f�ł���΃p���O���t�ł͂Ȃ�
			if (NOTPARAGRAPHS.containsKey(new Character(line.charAt(0)))) {
				if (line.charAt(0) != COMMENTFIRST || line.length() <= 1) {
					return false;
				} else {
					// �R�����g�������ʈ���
					String checkstr = line.substring(0, 2);
					if (COMMENT.equals(checkstr)) {
						return false;
					}
				}
			}
			
			// ����ȊO�̓p���O���t�Ƃ���B
			return true;
		}
		
		/**
		 * ���`�ςݕ����񂩂ǂ����`�F�b�N����
		 * FORMATED�v�f���ꕶ���ڂł���΁A���`�ςݕ�����Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���`�ςݕ�����ł����true
		 */
		public boolean isFormated(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// FORMATED�v�f���ꕶ���ڂł���΁A���`�ςݕ�����Ƃ���
			return (line.charAt(0) == FORMATED) ? true : false;
		}
		
		
		/**
		 * ���o���ǂ����`�F�b�N����
		 * HEADLINE�v�f���ꕶ���ڂł���΁A���o���Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���o���ł����true
		 */
		public boolean isHeadline(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// HEADLINE�v�f���ꕶ���ڂł���΁A���o���Ƃ���
			return (line.charAt(0) == HEADLINE) ? true : false;
		}
		
		
		/**
		 * ���p�ǂ����`�F�b�N����
		 * QUOTATION�v�f��UNQUOTATION�v�f���ꕶ���ڂł���΁A���p�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���p�ł����true
		 */
		public boolean isQuotation(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// QUOTATION�v�f��UNQUOTATION�v�f���ꕶ���ڂł���΁A���p�Ƃ���
			return (line.charAt(0) == QUOTATION || line.charAt(0) == UNQUOTATION) ? true : false;
		}
		
	}
}
