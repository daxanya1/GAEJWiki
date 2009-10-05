package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.ChildOnlyInlineBase;

/**
 * WikiObject
 * inline WikiName
 * @author daxanya
 *
 *
 *�s���ŁA1�ȏ�̑啶����1�ȏ�̏�������1�ȏ�̑啶����1�ȏ�̏������̑g���킹����Ȃ锼�p//�������WikiName�ɂȂ�܂��B

WikiName�̒��ɂ́A�S�p�����┼�p�󔒕����A�L���A�������܂߂邱�Ƃ͂ł��܂���B
WikiName�́APukiWiki���̃y�[�W���ɂȂ�܂��B���łɑ��݂���y�[�W�ł���΂��̃y�[�W�ւ̃����N�������I�ɓ\���܂��B���݂��Ȃ��ꍇ��WikiName�̌���?�������I�ɕt���A���̃y�[�W��V�K�쐬���邽�߂̃����N���\���܂��B
WikiName�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
WikiName�́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B
 *
 * * ��������
 * �y�[�W�����邩�Ȃ����̊m�F�́Ainline�̃p�[�X���Ɋm�F���A�Ȃ��ꍇ�̓t���O���Z�b�g���Ă����܂��B
 * �t���O���Z�b�g����Ă���ꍇ�A?�̕�����t�����܂��B(page�Ɠ�������)
 */

public class WikiNameInline extends ChildOnlyInlineBase {

	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append("wikiname");
		sb.append("|");
		sb.append(toString());
		sb.append("|");
		return sb.toString();
	}
	

	@Override
	protected void checkPage(WikiInlineParser parser) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getPattern() {
		return WIKINAMEFORMATPATTERN;
	}
	
	/**
	 * WikiName���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, WIKINAMEFORMATPATTERN);
		}
	}

}
