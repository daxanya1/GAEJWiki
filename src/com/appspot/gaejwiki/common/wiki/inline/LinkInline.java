package com.appspot.gaejwiki.common.wiki.inline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class LinkInline  implements WikiObjectInlineI {

	private String rawdata = null;
	private String alias = null;
	private String interwiki = null;
	private String param = null;
	private WikiObjectInlineI parent = null;
	private boolean urlflag = false;
	
	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}
	
	@Override
	public void set(String str, WikiInlineParser parser) {
		rawdata = str;
		new Sub().matchSet(rawdata);

	}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public String toHtmlString() {
		return "";
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append(new Boolean(urlflag).toString());
		sb.append("|");
		if (alias != null) sb.append(alias);
		sb.append("|");
		if (interwiki != null) sb.append(interwiki);
		sb.append("|");
		if (param != null) sb.append(param);
		sb.append("|");
		return sb.toString();
	}
	
	public class Sub {
		
		// ���K�\���ɂ����āA�K�v�ȏ������o��
		public void matchSet(String str) {
			{
				Pattern pattern1 = Pattern.compile(LINKFORMATPATTERN1);
				Matcher matcher1 = pattern1.matcher(str);
				if (matcher1.find()) {
					matchSet1(matcher1);
					return;
				}
			}

			{
				Pattern pattern2 = Pattern.compile(LINKFORMATPATTERN2);
				Matcher matcher2 = pattern2.matcher(str);
				if (matcher2.find()) {
					matchSet1(matcher2);
					return;
				}
			}

			{
				Pattern pattern3 = Pattern.compile(LINKFORMATPATTERN3);
				Matcher matcher3 = pattern3.matcher(str);
				if (matcher3.find()) {
					matchSet2(matcher3);
					return;
				}
			}

			{
				Pattern pattern4 = Pattern.compile(LINKFORMATPATTERN4);
				Matcher matcher4 = pattern4.matcher(str);
				if (matcher4.find()) {
					matchSet2(matcher4);
					return;
				}
			}

			{
				Pattern pattern5 = Pattern.compile(LINKFORMATPATTERN5);
				Matcher matcher5 = pattern5.matcher(str);
				if (matcher5.find()) {
					matchSet3(matcher5);
					return;
				}
			}

			{
				Pattern pattern6 = Pattern.compile(LINKFORMATPATTERN6);
				Matcher matcher6 = pattern6.matcher(str);
				if (matcher6.find()) {
					matchSet4(matcher6);
					return;
				}
			}
			
			return;
		}
		
		public void matchSet1(Matcher matcher) {
			urlflag = true;
			if (matcher.groupCount() >= 1) {
				alias = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				param = matcher.group(2);
			}
		}

		
		public void matchSet2(Matcher matcher) {
			urlflag = true;
			if (matcher.groupCount() >= 1) {
				param = matcher.group(1);
			}
		}
		
		public void matchSet3(Matcher matcher) {
			urlflag = false;
			if (matcher.groupCount() >= 1) {
				alias = matcher.group(1);
			}
			if (matcher.groupCount() >= 3) {
				interwiki = matcher.group(3);
			}
			if (matcher.groupCount() >= 4) {
				param = matcher.group(4);
			}
		}

		public void matchSet4(Matcher matcher) {
			urlflag = false;
			if (matcher.groupCount() >= 1) {
				interwiki = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				param = matcher.group(2);
			}
		}

	}

	/**
	 * �����N���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			int length = 0;
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN1);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN2);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN3);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN4);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN5);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, LINKFORMATPATTERN6);
			if (length > 0) { return length; }

			
			return 0;
		}
	}

}
