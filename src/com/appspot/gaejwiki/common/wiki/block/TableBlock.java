package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;

/**
 * WikiObject
 * �\�g
 * @author daxanya
 * 
 * --
 * 
�s������ | �ŃC�����C���v�f����؂邱�Ƃŕ\�g�݂ɂȂ�܂��B

�e�v�f�̐擪�ɉ��L�̋L�q�q���w��ł��܂��B
LEFT:
CENTER:
RIGHT:
BGCOLOR(�F):
COLOR(�F):
SIZE(�T�C�Y):
�v�f�̕\���ʒu�y�єw�i�F�E�����F�E�����T�C�Y(px�P��)���w�肵�܂��B�f�t�H���g�͍��񂹂ɂȂ�܂��B
�\�g�݂�	�e�Z���̗v�f�̔z�u��	�ւ���T���v��
����	�Z���^�����O	�E��
�E��	����	�Z���^�����O
�s����c���L�q����ƁA�����w��s�ƂȂ�܂��B�����w��s�ł́A���̋L�q�q���w��ł��܂��B
LEFT:
CENTER:
RIGHT:
BGCOLOR(�F):
COLOR(�F):
SIZE(�T�C�Y):
�L�q�q�̌��ɐ��l���L�q����ƁA�Z������px�P�ʂŎw��ł��܂��B
�s����h���L�q����ƁA�w�b�_�s(thead)�ɂȂ�܂��B
�s����f���L�q����ƁA�t�b�^�s(tfoot)�ɂȂ�܂��B
�Z�����̃C�����C���v�f�̐擪��~��t����ƁA�w�b�_(th)�ɂȂ�܂��B
�Z������ > ��P�ƂŋL�q����ƉE�̃Z���ƘA�����܂�(colspan)�B
�Z������ ~ ��P�ƂŋL�q����Ə�̃Z���ƘA�����܂�(rowspan)�B
�\�g�݂́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�\�g�݂́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B

 *
 * --
 */
public class TableBlock extends SameAddBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {

		/**
		 * Table���ǂ����`�F�b�N����
		 * TABLE�v�f���ꕶ���ڂł���΁ATable�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return Table�ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// TABLE�v�f���ꕶ���ڂł���΁ATable�Ƃ���
			return (line.charAt(0) == TABLE) ? true : false;
		}
	}
}
