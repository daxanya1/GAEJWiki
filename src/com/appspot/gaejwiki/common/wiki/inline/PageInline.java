package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ChildOnlyInlineBase;

/**
 * WikiObject
 * inline �y�[�W��
 * @author daxanya
 *
 *
 *�s���� [[ �� ]] �ň͂܂ꂽ������̓y�[�W���ɂȂ�܂��B

�y�[�W���̒��ɂ́A�S�p�����A�L���A�����A���p�󔒕������܂߂邱�Ƃ��ł��܂��B
�y�[�W���̒��ɂ́A"#&<> ���܂߂邱�Ƃ͂ł��܂���B
���łɑ��݂���y�[�W�ł���΂��̃y�[�W�ւ̃����N�������I�ɓ\���܂��B���݂��Ȃ��ꍇ�̓y�[�W���̌���?�������I�ɕt���A���̃y�[�W��V�K�쐬���邽�߂̃����N���\���܂��B
[[�y�[�W��#�A���J�[��]]
�y�[�W���ɃA���J�[�������邱�Ƃ��ł��܂��B
�y�[�W���́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�y�[�W���́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B
 *
 */

public class PageInline extends ChildOnlyInlineBase {

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("page");
		sb.append("|");
		sb.append(getData());
		return sb.toString();
	}
	
	@Override
	public String getPattern() {
		return PAGEFORMATPATTERN;
	}
	
	/**
	 * �y�[�W�����ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, PAGEFORMATPATTERN);
		}
	}
}
