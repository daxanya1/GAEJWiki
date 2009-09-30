package com.appspot.gaejwiki.common.wiki.inline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI.Checker.Util;
import com.appspot.gaejwiki.common.wiki.inline.base.YesChildNoParentInlineBase;

/**
 * WikiObject
 * inline Link
 * @author daxanya
 *
 *
 *�s���̃y�[�W���`���̕�����̒��ŁA: ��2�̕��������؂��InterWiki�ɂȂ�܂��B:�̑O�ɂ�InterWikiName�̃y�[�W�Œ�`����InterWikiName���A: �̌��ɂ̓y�[�W�����L�q���܂��B

[[InterWikiName:�y�[�W��#�A���J�[��]]
�y�[�W���̌��ɃA���J�[�������邱�Ƃ��ł��܂��B
InterWikiName�̒��ɂ́A�S�p�������܂߂邱�Ƃ��ł��܂��B
InterWikiName�̒��ɂ́A���p�󔒕������܂߂邪�ł��܂��B
InterWiki�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
InterWiki�́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B
 *
 *
 *�s����InterWikiName�̌`���̕�����̒��ŁA: �̑O�ɔC�ӂ̃����N�����A: �̌���URL���L�q����ƁA���̃����N����URL�ɑ΂��郊���N��\�邱�Ƃ��ł��܂��B

�����N���ɂ́A�S�p�����┼�p�󔒕������܂߂邱�Ƃ��ł��܂��B���[���A�h���X���w�肷��ꍇ�́Amailto:���������ɃA�h���X�������L�q���܂��B
http://www.example.com/
https://www.example.com/
ftp://ftp.example.com/
news://news.example.com/
foo@example.com
�s���ɒ��ځAURL���L�q�����ꍇ�͎����I�ɂ���URL�ɑ΂��郊���N���\���܂��B
�L�q����URL���摜�t�@�C���ł���ꍇ�́A���̉摜��\�����܂��B
�����N�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�����N�́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B
 *
 *
 *�s���̃y�[�W���`���̕�����̒��ŁA> ��2�̕��������؂�ƃG�C���A�X�ɂȂ�܂��B > �̑O�ɂ̓G�C���A�X�����A> �̌��ɂ̓y�[�W�����L�q���܂��B

�G�C���A�X��PukiWiki���̃y�[�W���Ƃ͕ʂ̃G�C���A�X���ŁA�w�肵���y�[�W�ւ̃����N��\��܂��B

[[�G�C���A�X��>�y�[�W��#�A���J�[��]]
[[�G�C���A�X��>#�A���J�[��]]
�y�[�W���ɃA���J�[����t�������ăA���J�[�ւ̃����N��\�邱�Ƃ��ł��܂��B�y�[�W���ƃA���J�[���̊Ԃɂ�#�����܂��B�J�����g�y�[�W�̃A���J�[�փ����N��\��ꍇ�̓y�[�W�����ȗ����邱�Ƃ��ł��܂��B
[[�G�C���A�X��>http://www.example.com/]]
[[�G�C���A�X��>https://www.example.com/]]
[[�G�C���A�X��>ftp://ftp.example.com/]]
[[�G�C���A�X��>news://news.example.com/]]
[[�G�C���A�X��>foo@example.com]]
[[�G�C���A�X��>InterWikiName:�y�[�W��]]
[[�G�C���A�X��>InterWikiName:�y�[�W��#�A���J�[��]]
�G�C���A�X��URL��InterWiki�Ƒg�ݍ��킹�邱�Ƃ��ł��܂��B
[[�G�C���A�X��:http://www.example.com/]]
[[�G�C���A�X��:https://www.example.com/]]
[[�G�C���A�X��:ftp://ftp.example.com/]]
[[�G�C���A�X��:news://news.example.com/]]
[[�G�C���A�X��:foo@example.com]]
URL���w�肷��ꍇ�́A>�̑����:���g�p�ł��܂��B
�G�C���A�X���̒��ɂ́A�S�p�������܂߂邱�Ƃ��ł��܂��B
�G�C���A�X���̒��ɂ́A���p�󔒕������܂߂邱�Ƃ��ł��܂��B
�K�w�����ꂽ�y�[�W�ł́A�y�[�W���𑊑Ύw�肷�邱�Ƃ��ł��܂��B
�G�C���A�X�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�G�C���A�X�́A���̃C�����C���v�f���q�v�f�ɂł��܂��B
 *
 */

public class LinkInline extends YesChildNoParentInlineBase {

	/**
	 * �����N���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, LINKFORMATPATTERN);
		}
	}

}
