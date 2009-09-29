package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;

/**
 * WikiObject
 * ���`�ς݃e�L�X�g
 * 
 * --
 * 
�s�������p�󔒂Ŏn�܂�s�͐��`�ς݃e�L�X�g�ƂȂ�܂��B�s�̎����܂�Ԃ��͍s�Ȃ��܂���B

���`�ς݃e�L�X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
���`�ς݃e�L�X�g�́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B
���`�ς݃e�L�X�g�́A���ׂĂ̎q�v�f�𕶎���Ƃ��Ĉ����܂��B
 * 
 * --
 * 
 * @author daxanya
 *
 */
public class FormatedBlock extends SameAddBlockBase  {

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * ���`�ςݕ����񂩂ǂ����`�F�b�N����
		 * FORMATED�v�f���ꕶ���ڂł���΁A���`�ςݕ�����Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���`�ςݕ�����ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// FORMATED�v�f���ꕶ���ڂł���΁A���`�ςݕ�����Ƃ���
			return (line.charAt(0) == FORMATED) ? true : false;
		}
	}
}
