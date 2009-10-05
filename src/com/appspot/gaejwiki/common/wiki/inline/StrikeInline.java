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
	public String getDebugStringHeader() {
		return "strike";
	}

	@Override
	public String toHtmlStringHeader() {
		return "<del>";
	}
	
	@Override
	public String toHtmlStringFooter() {
		return "</del>";
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
