package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;

/**
 * WikiObject
 * �ԍ��Ȃ����X�g
 * @author daxanya
 *
 * --
 *
�s���� - ���w�肷��ƁA�ԍ��Ȃ����X�g�ɂȂ�܂��B�ԍ��Ȃ����X�g�� -�A--�A--- ��3���x������܂��B

�ԍ��Ȃ����X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̃��X�g�\���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���p���̎q�v�f�ɂ���ꍇ�́A���x���𑝂₳���ɋL�q���܂��B
-�̒���� ~���L�q����ƒi�����q�v�f�ɂ��邱�Ƃ��ł��܂��B
�ԍ��Ȃ����X�g�́A���X�g�̐擪���C�����C���v�f�܂��͒i���ł���ꍇ�Ɍ���A���X�g�̎��̍s�ɑ��̃u���b�N�v�f���L�q���邱�ƂŁA���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 * --
 */
public class UnnumberedListBlock extends ListBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {

		/**
		 * �ԍ��Ȃ����X�g�ǂ����`�F�b�N����
		 * ��ɐ��������ǂ����𒲂ׂ�
		 * �������łȂ���΁ANUMBEREDLIST�v�f��UNNUMBERED�v�f���ꕶ���ڂł���΁A���X�g�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���X�g�ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// �������ł���ΈႤ
			if (new HorizonBlock.Checker().isThis(line)) {
				return false;
			}
			
			// NUMBEREDLIST�v�f��UNNUMBEREDLIST�v�f���ꕶ���ڂł���΁A���X�g�Ƃ���
			return (line.charAt(0) == UNNUMBEREDLIST) ? true : false;
		}
	}
}
