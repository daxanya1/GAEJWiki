package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;



/**
 * WikiObject
 * ���o��
 * @author daxanya
 *
 * --
 * 
�s���� * ���L�q����ƁA���o���ɂȂ�܂��B���o���� *�A**�A*** ��3�i�K����܂��B

���o���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ͂ł��܂���B���o����������Ƒ��̃u���b�N�v�f�͏I�����܂��B
���o���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 * 
 * --

 */
public class HeadlineBlock extends NoChildParentBlockBase {

	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * ���o���ǂ����`�F�b�N����
		 * HEADLINE�v�f���ꕶ���ڂł���΁A���o���Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���o���ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// HEADLINE�v�f���ꕶ���ڂł���΁A���o���Ƃ���
			return (line.charAt(0) == HEADLINE) ? true : false;
		}
	}
}
