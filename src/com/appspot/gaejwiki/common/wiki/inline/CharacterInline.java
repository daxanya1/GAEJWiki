package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.common.wiki.inline.base.OneCharacterInlineBase;

/**
 * WikiObject
 * inline ������
 * @author daxanya
 *
 *
 *������̓C�����C���v�f�ł��B

������́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
������́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B
 *
 */

public class CharacterInline extends OneCharacterInlineBase {

	@Override
	public String toHtmlString() {
		return TextUtils.escapeHtmlString(getRawData());
	}

	@Override
	public String toDebugString() {
		return toString();
	}
	
	@Override
	public String toString() {
		return getRawData();
	}
	
	/**
	 * ��������P�����Ƃ���
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return 1;
		}
	}

}
