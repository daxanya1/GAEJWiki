package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.OneCharacterInlineBase;

/**
 * WikiObject
 * inline ���s
 * @author daxanya
 *
 *
 *�s����~�������ƍs�����s�ɂȂ�܂��B�s�����s�̓u���b�N�v�f���ł̉��s�ɂȂ�܂��B

�s�����s�̎��̍s�̍s�������͖����ɂȂ�A������Ƃ��Ĉ����܂��B
�s�����s�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ͂ł��܂���B
�s�����s�́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
�s�����s�́A��`���X�g�̒�`��A�\�g�݂̗v�f�A#�Ŏn�܂�u���b�N�v�f�̃p�����^�̒��ł͎g�p�ł��܂���B
 *
 */

public class NewlineInline extends OneCharacterInlineBase {

	@Override
	public String toHtmlString() {
		return "<br />";
	}
	
	@Override
	public String toDebugString() {
		return toHtmlString();
	}
	
	@Override
	public String toString() {
		return "~";
	}
	
	/**
	 * ~���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return (str != null && str.length() == 1 && str.charAt(0) == TILDE) ? 1 : 0;
		}
	}
}
