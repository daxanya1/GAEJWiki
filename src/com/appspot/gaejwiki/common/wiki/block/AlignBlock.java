package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;

/**
 * WikiObject
 * ���񂹁E�Z���^�����O�E�E��
 * @author daxanya
 * 
 * --
 * 
�s���� LEFT:�A CENTER:�A RIGHT: ���L�q����ƁA�C�����C���v�f�����񂹁A�Z���^�����O�A�E�񂹂���܂��B

LEFT:�ACENTER:�ARIGHT:�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
LEFT:�ACENTER:�ARIGHT:�́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B

 *
 * --
 */
public class AlignBlock extends YesChildNoAddlineBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {

		/**
		 * �z�u�u���b�N���ǂ����`�F�b�N����
		 * �z�u�v�f���ꕶ���ڂł���΁A�z�u�u���b�N�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return �z�u�u���b�N�ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// LEFT:,CENTER:,RIGHT:�̂ǂꂩ��������true
			return (equalsSubstring(line, CENTERFORMAT)) ? true : 
				(equalsSubstring(line, LEFTFORMAT)) ? true :
				(equalsSubstring(line, RIGHTFORMAT)) ? true : false;
		}
		
		private boolean equalsSubstring(String line, String key) {
			if (line.length() < key.length()) {
				return false;
			}
			return key.equals(line.substring(0, key.length()));
		}
	}
}
