package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildParentInlineBase;

/**
 * WikiObject
 * inline �Α�
 * @author daxanya
 *
 *
 *�s���̃C�����C���v�f�� ''' �ł͂��ނƁA�C�����C���v�f�� �Α̕\�� �ɂȂ�܂��B

�Α̂́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�Α̂́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 */

public class ItalicInline extends YesChildParentInlineBase {

	/**
	 * �C�^���b�N���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, ITALICFORMATPATTERN);
		}
	}
}
