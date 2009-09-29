package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;

/**
 * WikiObject
 * ���p
 * @author daxanya
 *
 * --
 *
�s���� > ���w�肷��ƁA���p���ɂȂ�܂��B���p���� >�A>>�A>>> ��3���x������܂��B

���p���̒��́A�u���b�N�v�f�𖾎����Ȃ�����A�i���ƂȂ�܂��B
���p���́A��s��������܂Ōp�����܂��B
���p�����̒i���́A�V���Ȉ��p���܂��̓u���b�N�v�f��������܂Ōp�����܂��B
���p���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̈��p���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���X�g�\���̎q�v�f�ɂ���ꍇ�̓��x����1�i���₳���ɋL�q���܂��B
���p���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B���p���̎q�v�f�ƂȂ郊�X�g�\���̓��x����1�i���₳���ɋL�q���܂��B
���X�g�\�����̈��p������E�o����ꍇ�ŁA���X�g�\�����p������ꍇ�́A<�A<<�A<<<���s���ɋL�q���܂��B
 *
 * --
 */
public class QuotationBlock extends ListBlockBase{

	static public class Checker implements WikiObjectBlockI.Checker {

		/**
		 * ���p�ǂ����`�F�b�N����
		 * QUOTATION�v�f��UNQUOTATION�v�f���ꕶ���ڂł���΁A���p�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return ���p�ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// QUOTATION�v�f��UNQUOTATION�v�f���ꕶ���ڂł���΁A���p�Ƃ���
			return (line.charAt(0) == QUOTATION || line.charAt(0) == UNQUOTATION) ? true : false;
		}
	}
}
