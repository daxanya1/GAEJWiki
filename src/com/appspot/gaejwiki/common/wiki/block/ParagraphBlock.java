package com.appspot.gaejwiki.common.wiki.block;

import java.util.HashMap;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;

/**
 * WikiObject
 * �i��
 * @author daxanya
 * 
 * --
 * 
���̃u���b�N�v�f�𖾎����Ȃ�����A�i���ƂȂ�܂��B

�s���� ~ ���w�肵���ꍇ���i���ɂȂ�܂��B�s�������̕���(~�A-�A+�A:�A>�A|�A#�A//)��ʏ�̕����Ƃ��Ēi���̐擪�ɏ��������ꍇ�́A�s����~���L�q���ď������Ƃ��ł��܂��B

�i���̐擪��1����������������܂��B�A���A�ԍ��Ȃ����X�g�\���A�ԍ��t�����X�g�\���A���p�����̒i���ł͎���������܂���B��`���X�g���̒i���̐擪��1����������������܂��B
�i���́A�V���ȃu���b�N�v�f��������܂Ōp�����܂��B
�i���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�i���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 *
 * --
 */
public class ParagraphBlock extends SameAddBlockBase {

	// �s�������̕���(-�A+�A:�A>�A|�A#�A//)
	private static final char[] NOTPARAGRAPHCHAR = { QUOTATION, UNQUOTATION, FORMATED, UNNUMBEREDLIST, NUMBEREDLIST, DEFINEDLIST, HASH, HEADLINE, TABLE, CSV, COMMENTFIRST };
	private static final HashMap<Character, Boolean> NOTPARAGRAPHS = new HashMap<Character, Boolean>();
	
	static {
		for (char notpara : NOTPARAGRAPHCHAR) {
			NOTPARAGRAPHS.put(new Character(notpara), Boolean.TRUE);
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * �p���O���t��block���ǂ����`�F�b�N����
		 * ~���擪�ł���΁A�p���O���t
		 * ���̃u���b�N�v�f�ł���΃p���O���t�ł͂Ȃ�
		 * ����ȊO�̓p���O���t�Ƃ���
		 * @param line�@��s���̕�����
		 * @return �p���O���t�ł����true
		 */
		public boolean isThis(String line) {
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
					if (line.charAt(0) == COMMENTFIRST) {
						// �R�����g���ǂ����m�F
						if (new CommentBlock.Checker().isThis(line)) {
							return false;
						}
					}
					if (line.charAt(0) == DEFINEDLIST) {
						// ��`���X�g�̏ꍇ �ǂ�����|������Β�`���X�g�A����ȊO�͒i������
						if (new DefinedListBlock.Checker().isThis(line)) {
							return false;
						}
					}
				}
			}
		
			// ����ȊO�̓p���O���t�Ƃ���B
			return true;
		}
	}

}
