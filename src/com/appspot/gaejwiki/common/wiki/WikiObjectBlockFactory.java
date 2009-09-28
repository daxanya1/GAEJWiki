package com.appspot.gaejwiki.common.wiki;

import java.util.HashMap;

public class WikiObjectBlockFactory {

	public static final char PARAGRAPH = '~';
	public static final char QUOTATION = '>';
	public static final char UNQUOTATION = '<';
	public static final char FORMATED = ' ';
	public static final char UNNUMBEREDLIST = '-';
	public static final char NUMBEREDLIST = '+';
	public static final char DEFINEDLIST = ':';
	public static final char CONTENTS = '#';
	public static final char HEADLINE = '*';
	public static final char TABLE = '|';
	public static final char CSV = ',';
	public static final char COMMENTFIRST = '/';
	
	public static final String COMMENT = "//";
	public static final String HORIZON = "----";
	public static final char DEFINEDLIST_SECONDCHAR = '|';
	
	
	public WikiObjectI createWikiObject(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		
		BlockCheck blockcheck = new BlockCheck();
		
		if (blockcheck.isParagraph(line)) {
			return createParagraphBlock();
		}
		
		if (blockcheck.isFormated(line)) {
			return createFormatedBlock();
		}
		
		if (blockcheck.isHeadline(line)) {
			return createHeadlineBlock();
		}
		
		if (blockcheck.isQuotation(line)) {
			return createQuotationBlock();
		}
		
		// 
		return null;
	}
	
	protected WikiObjectI createParagraphBlock() {
		return new ParagraphBlock();
	}
	
	protected WikiObjectI createFormatedBlock() {
		return new FormatedBlock();
	}
	
	protected WikiObjectI createHeadlineBlock() {
		return new HeadlineBlock();
	}
	
	protected WikiObjectI createQuotationBlock() {
		return new QuotationBlock();
	}
	
	
	
	static public class BlockCheck {
		
		// �s�������̕���(-�A+�A:�A>�A|�A#�A//)
		private static final char[] NOTPARAGRAPHCHAR = { QUOTATION, UNQUOTATION, FORMATED, UNNUMBEREDLIST, NUMBEREDLIST, DEFINEDLIST, CONTENTS, HEADLINE, TABLE, CSV, COMMENTFIRST };
		private static final HashMap<Character, Boolean> NOTPARAGRAPHS = new HashMap<Character, Boolean>();
		
		static {
			for (char notpara : NOTPARAGRAPHCHAR) {
				NOTPARAGRAPHS.put(new Character(notpara), Boolean.TRUE);
			}
		}
		
		/**
		 * �R�����g�s���ǂ����m�F
		 * @param line
		 * @return �R�����g�s�Ȃ�true
		 */
		public boolean isComment(String line) {
			// �Q�����ȉ��Ȃ�R�����g�ł͂Ȃ�
			if (line == null || line.length() < 2) {
				return false;
			}
			
			// �Q�����ڂ܂Œ��ׂāA//��������true
			String checkstr = line.substring(0, 2);
			return (COMMENT.equals(checkstr)) ? true : false;
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
				if (line.charAt(0) != COMMENTFIRST && line.charAt(0) != DEFINEDLIST) {
					return false;
				} else {
					// �R�����g�ƒ�`���X�g�������ʈ���
					if (line.charAt(0) != COMMENTFIRST) {
						// �R�����g���ǂ����m�F
						if (isComment(line)) {
							return false;
						}
					}
					if (line.charAt(0) != DEFINEDLIST) {
						// ��`���X�g�̏ꍇ �ǂ�����|������Β�`���X�g�A����ȊO�͒i������
						if (isDefinedList(line)) {
							return false;
						}
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
		
		/**
		 * CSV�ǂ����`�F�b�N����
		 * CSV�v�f���ꕶ���ڂł���΁ACSV�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return CSV�ł����true
		 */
		public boolean isCsv(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// CSV�v�f���ꕶ���ڂł���΁ACSV�Ƃ���
			return (line.charAt(0) == CSV) ? true : false;
		}
		
		/**
		 * ��`���X�g�ǂ����`�F�b�N����
		 * ��`���X�g�v�f���ꕶ���ڂł���΁A��`���X�g�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ��`���X�g�ł����true
		 */
		public boolean isDefinedList(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// ��`���X�g�v�f���ꕶ���ڂł��A�ǂ�����|������Β�`���X�g�Ƃ���
			return (line.charAt(0) == DEFINEDLIST && line.indexOf(DEFINEDLIST_SECONDCHAR) >= 0) ? true : false;
		}
		
		/**
		 * ���������ǂ����`�F�b�N����
		 * �������v�f������΁A�������Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return �������ł����true
		 */
		public boolean isHorizon(String line) {
			if (line == null || line.length() < 4) {
				return false;
			}
			
			// �S�����ڂ܂Œ��ׂāA----��������true
			String checkstr = line.substring(0, 4);
			return (HORIZON.equals(checkstr)) ? true : false;
		}

		/**
		 * ���X�g�ǂ����`�F�b�N����
		 * ��ɐ��������ǂ����𒲂ׂ�
		 * �������łȂ���΁ANUMBEREDLIST�v�f��UNNUMBERED�v�f���ꕶ���ڂł���΁A���X�g�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���X�g�ł����true
		 */
		public boolean isList(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// �������ł���ΈႤ
			if (isHorizon(line)) {
				return false;
			}
			
			// NUMBEREDLIST�v�f��UNNUMBEREDLIST�v�f���ꕶ���ڂł���΁A���X�g�Ƃ���
			return (line.charAt(0) == NUMBEREDLIST || line.charAt(0) == UNNUMBEREDLIST) ? true : false;
		}
		

	}
}
