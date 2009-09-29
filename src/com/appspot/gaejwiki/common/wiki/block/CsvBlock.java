package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;


/**
 * WikiObject
 * CSV
 * @author daxanya
 * 
 * --
 * 
�s���ŃJ���}(,)���L�q���A�C�����C���v�f���J���}��؂�ŋL�q����ƕ\�g�݂ɂȂ�܂��B

�C�����C���v�f�̓_�u���N�H�[�e�[�V����(")�ň͂ނ��Ƃ��ł��܂��B�_�u���N�H�[�e�[�V�����ň͂ނ��ƂŁA�J���}(,)���܂ރC�����C���v�f���L�q�ł��܂��B
�_�u���N�H�[�e�[�V����(")�ň͂񂾃f�[�^�̒��ŁA�_�u���N�H�[�e�[�V������2��("")�����邱�ƂŁA�_�u���N�H�[�e�[�V����(")���܂ރC�����C���v�f���L�q�ł��܂��B
�C�����C���v�f�̑���ɃC�R�[����2��(==)�L�q����ƁAcolspan���Ӗ����܂��B
�C�����C���v�f�̍���1�ȏ�̔��p�󔒕������L�q����ƉE�񂹂ɁA�C�����C���v�f�̍��E��1�ȏ�̔��p�󔒕������L�q����ƃZ���^�����O�ɂȂ�܂��B
�\�g�݂́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�\�g�݂́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B

 *
 * --
 */
public class CsvBlock extends YesChildNoAddlineBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * CSV�ǂ����`�F�b�N����
		 * CSV�v�f���ꕶ���ڂł���΁ACSV�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return CSV�ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// CSV�v�f���ꕶ���ڂł���΁ACSV�Ƃ���
			return (line.charAt(0) == CSV) ? true : false;
		}
	}
}
