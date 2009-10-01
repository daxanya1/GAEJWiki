package com.appspot.gaejwiki.common.wiki.inline;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WikiObject
 * inline &(){}�n �̂����A�q�v�f�ɂȂ邵�e�ɂ��Ȃ����
 * @author daxanya
 *
 *
 *�s���� &color �Ə����ƃC�����C���v�f�̕����F�Ɣw�i�F���w�肷�邱�Ƃ��ł��܂��B�w�i�F�͏ȗ��ł��܂��B

�F�̎w��́A���̂����ꂩ�̌`���ōs�Ȃ��܂��B
�F�L�[���[�h
#16�i��6��
#16�i��3��
�����F�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�����F�́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B

�s���� &size �������ƃC�����C���v�f�̕����T�C�Y���w�肷�邱�Ƃ��ł��܂��B�T�C�Y�̓s�N�Z���P��(px)�Ŏw�肵�܂��B�Ⴆ��20���w�肷��ƁA20�s�N�Z���̕����̑傫���ɂȂ�܂��B

�����T�C�Y�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�����T�C�Y�́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B

�s���� &ruby �������ƃC�����C���v�f�ɑ΂��郋�r���ӂ邱�Ƃ��ł��܂��B���r�ɑΉ����Ă��Ȃ��u���E�U�ł̓��r���{������ ( �� ) �Ɉ͂܂�ĕ\������܂��B

���r�\���̃��r�ɂ̓C�����C���v�f���L�q���܂��B�������A���r�̓�����A���Ғʂ�̕\���ɂȂ�Ƃ͌���܂���B
���r�\���́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
���r�\���́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
���r�\�������r�\���̎q�v�f�ɂ͂ł��܂���(�l�X�g�͂ł��܂���)�B
���ߓ��╶���T�C�Y�����������Ă��镔���ł̎g�p�́A���r�����ǂł��Ȃ��Ȃ�̂Ŕ����Ă��������B

�s���� &aname ���L�q����ƃ����N�̃A���J�[��ݒ肷�邱�Ƃ��ł��܂��B�����N�̔�ѐ�ɂ������ʒu�ɋL�q���܂��B

�A���J�[���́A���p�A���t�@�x�b�g����n�܂锼�p�A���t�@�x�b�g�E�����E�n�C�t���E�A���_�[�X�R�A����Ȃ镶������w�肵�܂��B
�A���J�[���̒��ɂ́A�S�p�����┼�p�󔒕����A���p�L�����܂߂邱�Ƃ͂ł��܂���B
�A���J�[�ݒ�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�A���J�[�ݒ�́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B

 *
 */

public class AmpersandChildParentInline implements WikiObjectInlineI {

	private String rawdata = null;
	private List<WikiObjectInlineI> childlist = null;
	private String ampersandtype = null;
	private String ampersandparam = null;
	private WikiObjectInlineI parent = null;
	
	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}
	
	@Override
	public void set(String str, WikiObjectInlineFactory factory) {
		rawdata = str;
		// �ċA�������s��
		String line = new Sub().matchSet(rawdata);
		if (line != null) {
			childlist = new WikiInlineParser().parseInline(factory, line);
		}
	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append(ampersandtype);
		sb.append("|");
		sb.append(ampersandparam);
		sb.append("|");
		if (childlist != null) {
			for (WikiObjectInlineI inline : childlist) {
				sb.append("c:/");
				sb.append(inline.toDebugString());
				sb.append("/:c");
			}
		}
		return sb.toString();
	}

	public class Sub {
		
		// ���K�\���ɂ����āA�K�v�ȏ������o��
		public String matchSet(String str) {
			Pattern pattern1 = Pattern.compile(AMPERSANDCHILDPARENTFORMATPATTERN1);
			Matcher matcher1 = pattern1.matcher(str);
			if (matcher1.find()) {
				return matchSet1(matcher1);
			}

			Pattern pattern2 = Pattern.compile(AMPERSANDCHILDPARENTFORMATPATTERN2);
			Matcher matcher2 = pattern2.matcher(str);
			if (matcher2.find()) {
				return matchSet2(matcher2);
			}

			Pattern pattern3 = Pattern.compile(AMPERSANDCHILDPARENTFORMATPATTERN3);
			Matcher matcher3 = pattern3.matcher(str);
			if (matcher3.find()) {
				return matchSet2(matcher3);
			}

			
			return null;
		}
		
		public String matchSet1(Matcher matcher) {
			if (matcher.groupCount() >= 1) {
				ampersandtype = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				ampersandparam = matcher.group(2);
			}

			return (matcher.groupCount() >= 3) ? matcher.group(3) : null;
		}
		
		public String matchSet2(Matcher matcher) {
			if (matcher.groupCount() >= 1) {
				ampersandtype = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				ampersandparam = matcher.group(2);
			}

			return (matcher.groupCount() >= 4) ? matcher.group(4) : null;
		}
	}

	/**
	 * &(){}�n���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		@Override
		public int getMatchLength(String str) {
			int length = 0;
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN1);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN2);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN3);
			if (length > 0) { return length; }
			
			return 0;
		}
	}

}
