package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ParentableInlineBase;

/**
 * WikiObject
 * inline ��������
 * @author daxanya
 *
 *
 *�s���̃C�����C���v�f��%%�ł͂��ނƁA�C�����C���v�f�Ɏ�������t���܂��B

������́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
������́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 */

public class StrikeInline extends ParentableInlineBase {

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("strike");
		sb.append("|");
		if (getChildList() != null) {
			for (WikiObjectInlineI inline : getChildList()) {
				sb.append("c:/");
				sb.append(inline.toDebugString());
				sb.append("/:c");
			}
		}
		return sb.toString();
	}

	@Override
	public String getPattern() {
		return STRIKEFORMATPATTERN;
	}
	
	/**
	 * �����������ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, STRIKEFORMATPATTERN);
		}
	}
}
