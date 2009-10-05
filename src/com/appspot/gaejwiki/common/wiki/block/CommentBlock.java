package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;


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

	
	@Override
	public String toHtmlString() {
		// �p�[�X�ΏۂƂȂ�Ȃ�
		assert(false);
		return null;
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		// �Ȃɂ����Ȃ�
	}
	
	@Override
	public int getLevel() {
		return -1;
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			// COMMENT.length()�ȉ��Ȃ�R�����g�ł͂Ȃ�
			if (line == null || line.length() < COMMENT.length()) {
				return false;
			}
			
			String checkstr = line.substring(0, COMMENT.length());
			return (COMMENT.equals(checkstr)) ? true : false;
		}
	}

}
