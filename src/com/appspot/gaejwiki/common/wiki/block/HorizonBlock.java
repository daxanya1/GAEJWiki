package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;



/**
 * WikiObject
 * ������
 * @author daxanya
 *
 * --
 * 
�s����4�ȏ�� - �������Ɛ������ɂȂ�܂��B

�������́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ͂ł��܂���B��������������Ƒ��̃u���b�N�v�f�͏I�����܂��B
�������́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 * 
 * --

 */
public class HorizonBlock extends NoChildParentBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * ���������ǂ����`�F�b�N����
		 * �������v�f������΁A�������Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return �������ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() < 4) {
				return false;
			}
			
			// �S�����ڂ܂Œ��ׂāA----��������true
			String checkstr = line.substring(0, 4);
			return (HORIZON.equals(checkstr)) ? true : false;
		}
	}
}
