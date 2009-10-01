package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ParentableInlineBase;

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

public class NoteInline extends ParentableInlineBase {

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("note");
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
		return NOTEFORMATPATTERN;
	}
	
	/**
	 * ���߂��ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, NOTEFORMATPATTERN);
		}
	}
}
