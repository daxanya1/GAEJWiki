package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildParentInlineBase;

/**
 * WikiObject
 * inline ����
 * @author daxanya
 *
 *
 *�s���ŃC�����C���v�f�� (( �� )) �ł͂��ނƁA����*3���쐬����A�s���ɒ��߂ւ̃����N���\���܂��B

���߂́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B�e�v�f�͒��ߕ��ł͂Ȃ��A���߂ւ̃����N�ɔ��f����܂��B
���߂́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B�q�v�f�͒��ߕ��ɔ��f����܂��B
 *
 */

public class NoteInline extends YesChildParentInlineBase {

	/**
	 * ���߂��ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, NOTEFORMATPATTERN);
		}
	}
}
