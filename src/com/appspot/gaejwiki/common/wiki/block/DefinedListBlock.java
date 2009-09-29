package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;


/**
 * WikiObject
 * ��`���X�g
 * @author daxanya
 * 
 * --
 * 
�s���� : �Ŏn�߁A| �L���ŋ�؂�ƁA��`���X�g�ɂȂ�܂��B��`���X�g�� :�A::�A::: ��3�i�K����܂��B��`���X�g�̒�`��A�������͏ȗ����邱�Ƃ��ł��܂��B�����̘A��������`���X�g���L�q���A2�ڈȍ~�̒�`����ȗ����邱�Ƃ�1�̒�`��ɑ΂��镡���̐��������L�q���邱�Ƃ��ł��܂��B

�s���� | ���Ȃ��ƒ�`���X�g�ɂ͂Ȃ�܂���B
��`��E�������́A�C�����C���v�f�̂݋L�q���邱�Ƃ��ł��܂��B
��`���X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̃��X�g�\���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���p���̎q�v�f�ɂ���ꍇ�́A���x���𑝂₳���ɋL�q���܂��B
| �̒���� ~ ���L�q����ƒi�����q�v�f�ɂ��邱�Ƃ��ł��܂��B
��`���X�g�́A��`���X�g�̎��̍s�ɑ��̃u���b�N�v�f���L�q���邱�ƂŁA���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 * --
 */
public class DefinedListBlock extends ListBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * ��`���X�g�ǂ����`�F�b�N����
		 * ��`���X�g�v�f���ꕶ���ڂł���΁A��`���X�g�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ��`���X�g�ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// ��`���X�g�v�f���ꕶ���ڂł��A�ǂ�����|������Β�`���X�g�Ƃ���
			return (line.charAt(0) == DEFINEDLIST && line.indexOf(DEFINEDLIST_SECONDCHAR) >= 0) ? true : false;
		}
	}
}