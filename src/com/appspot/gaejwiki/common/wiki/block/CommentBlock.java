package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;


/**
 * WikiObject
 * �R�����g
 * @author daxanya
 * 
 * --
 * 
�s���� // ���w�肷��ƁA�R�����g�s�ɂȂ�܂��B�R�����g�s�͏o�͂���Ȃ��s�ł��B

�R�����g�s�́A���̗v�f�Ɩ��֌W�ɍs�P�ʂłǂ̈ʒu�ɂ��L�q�ł��܂��B
�R�����g�s�́A�O��̑��̗v�f�ɉ���e�����y�ڂ��܂���B

���{���u���b�N�ł͂Ȃ����A�\���I�Ƀu���b�N�����Ƃ���(�p�[�T�ŏ�������)
 *
 * --
 */
public class CommentBlock extends NoChildParentBlockBase {

	/**
	 * �R�����g�s���ǂ����m�F
	 * @param line
	 * @return �R�����g�s�Ȃ�true
	 */
	static public class Checker implements WikiObjectBlockI.Checker {
		
		public boolean isThis(String line) {
			// �Q�����ȉ��Ȃ�R�����g�ł͂Ȃ�
			if (line == null || line.length() < 2) {
				return false;
			}
			
			// �Q�����ڂ܂Œ��ׂāA//��������true
			String checkstr = line.substring(0, 2);
			return (COMMENT.equals(checkstr)) ? true : false;
		}
	}
}
