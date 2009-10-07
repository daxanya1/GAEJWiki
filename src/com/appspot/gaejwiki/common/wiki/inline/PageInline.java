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
 *
 * ��������
 * �y�[�W�����邩�Ȃ����̊m�F�́Ainline�̃p�[�X���Ɋm�F���A�Ȃ��ꍇ�̓t���O���Z�b�g���Ă����܂��B
 * �t���O���Z�b�g����Ă���ꍇ�A?�̕�����t�����܂��B
 */

public class PageInline extends ChildOnlyInlineBase {

	private boolean existpage = false;
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		if (existpage) {
			sb.append(new Sub().getExistHtmlString(toString()));
		} else {
			sb.append(new Sub().getNonExistHtmlString(toString()));
		}
		return sb.toString();
	}
	

	@Override
	protected void checkPage(WikiInlineParser parser) {
		existpage = parser.checkPage(toString());
		
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("page");
		sb.append("|");
		sb.append(toString());
		return sb.toString();
	}
	
	@Override
	public String getPattern() {
		return PAGEFORMATPATTERN;
	}
	
	static public class Checker implements WikiObjectInlineI.Checker {

		@Override
		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, PAGEFORMATPATTERN);
		}
	}

}
