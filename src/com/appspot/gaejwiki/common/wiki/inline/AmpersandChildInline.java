package com.appspot.gaejwiki.common.wiki.inline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WikiObject
 * inline &()�n �̂����A�q�v�f�ɂȂ�邪�e�v�f�ɂȂ�Ȃ�����
 * @author daxanya
 *
 *
 *
 *�s���� &br; �������ƁA�����ŉ��s����܂��B�s���� ~ �ƈႢ�A��`���X�g�̒�`���\�g�̗v�f�A# �Ŏn�܂�u���b�N�v�f�̃p�����^�̒��ł��g�p�ł��܂��B

�s�����s�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�s�����s�́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B

&ref(�Y�t�t�@�C����);
&ref(�t�@�C����URL);
�s���� &ref ���L�q����ƁA�Y�t�t�@�C���܂��͎w�肳�ꂽURL�ɂ���t�@�C���ւ̃����N��\��t���邱�Ƃ��ł��܂��B�t�@�C�����摜�t�@�C���̏ꍇ�́A���̉摜��\�����܂��B

&ref �ɂ́A�J���}�ŋ�؂��ĉ��L�̃p�����^���w��ł��܂��B�p�����^���ȗ������ꍇ�̓f�t�H���g�l�ƂȂ�܂��B
�Y�t�t�@�C���̃y�[�W
�Y�t�t�@�C�������݂���y�[�W�����w�肵�܂��B�f�t�H���g�͌��݂̃y�[�W�ł��B
���̃p�����^���w�肷��ꍇ�́A�Y�t�t�@�C�����̎��ɋL�q���܂��B
nolink
�f�t�H���g�ł͓Y�t�t�@�C���ւ̃����N�������܂����Anolink ���w�肷��ƃ����N�𒣂�܂���B
��֕�����
�t�@�C�����̑���ɕ\�����镶�����摜�̑�֕�������w��ł��܂��B�w�肵�Ȃ��ꍇ�́A�t�@�C�����ɂȂ�܂��B
��֕�����ɂ͕�����ȊO�̃C�����C���v�f���܂߂邱�Ƃ͂ł��܂���B�y�[�W���A������ȊO�̃C�����C���v�f���L�q���Ă�������Ƃ��Ĉ����܂��B
���̃p�����^���w�肷��ꍇ�́A�Ō�ɋL�q���܂��B
&ref �́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
&ref �́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


�s���� &counter ���L�q����Ƃ��̃y�[�W�ɃA�N�Z�X�����l�̐���\�����邱�Ƃ��ł��܂��B

&counter �ɂ͎��̃I�v�V�������w��ł��܂��B�I�v�V�������ȗ������ꍇ��total���w�肳�ꂽ���̂Ƃ݂Ȃ���܂��B
today
�����̃A�N�Z�X����\�����܂��B
yesterday
����̃A�N�Z�X����\�����܂��B
total
�A�N�Z�X������\�����܂��B
�J�E���^�\���́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�J�E���^�\���́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


&online;
�s���� &online ���L�q����ƌ��݃A�N�Z�X���̐l����\�����邱�Ƃ��ł��܂��B

�I�����C���\���́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�I�����C���\���́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


&version;
�s���� &version ���L�q�����PukiWiki�̃o�[�W������\�����邱�Ƃ��ł��܂��B

�o�[�W�����\���́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�o�[�W�����\���́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


�s���� &t; �Ə����ƁA�^�u�R�[�h�ɒu������܂��B

�ʏ�A�t�H�[�����ł̓L�[�{�[�h����^�u�R�[�h�𒼐ړ��͂ł��Ȃ�*4�̂ŁA�^�u�R�[�h����͂������ʒu��&t;���L�q����ƁA�ۑ����Ƀ^�u�R�[�h�ɒu�����܂��B

�^�u�R�[�h�́A�L�����������܂��Ƃ��ɒu������ċL�^����܂��B
�^�u�R�[�h�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�^�u�R�[�h�́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


�s���� &page; �y�� &fpage; �Ə����ƁA�ҏW���̃y�[�W���ɒu������܂��B�y�[�W�����K�w�����ꂽ�y�[�W�Łuhogehoge/hoge�v�ƂȂ��Ă���ꍇ�A &page; �́uhoge�v�ɁA &fpage; �́uhogehoge/hoge�v�ɒu������܂��B�K�w������Ă��Ȃ��y�[�W�ł́A &page; �� &fpage; �������ɂȂ�܂��B

�y�[�W���u�������́A�L�����������܂��Ƃ��ɒu������ċL�^����܂��B
�y�[�W���u�������́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�y�[�W���u�������́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B

�s���� &date; �Ə����ƁA�X�V���̓��t�ɒu������܂�
�s���� &time; �Ə����ƁA�X�V���̎����ɒu������܂�
�s���� &now; �Ə����ƁA�X�V���̓����ɒu������܂�
&date;�A &time;�A &now;�́A�L�����������܂��Ƃ��ɒu������ċL�^����܂��B
&date;�A &time;�A &now;�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
&date;�A &time;�A &now;�́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


�s���� &_date; �Ə����ƁA�\�����̓��t�ɒu������ďo�͂���܂��B
�s���� &_time; �Ə����ƁA�\�����̎����ɒu������ďo�͂���܂��B
�s���� &_now; �Ə����ƁA�\�����̓����ɒu������ďo�͂���܂��B
�s���� &lastmod; �Ə����ƁA���̃y�[�W�̍ŏI�X�V�����ɒu������ďo�͂���܂��B
�y�[�W�����w�肷��ƁA���̎w�肳�ꂽ�y�[�W�̍ŏI�X�V�����ɒu������ďo�͂���܂��B
&_date;�A &_time;�A &_now;�A &lastmod;�́A�L�����\�������Ƃ��ɒu������ďo�͂���܂��B
&_date;�A &_time;�A &_now;�A &lastmod;�́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
&_date;�A &_time;�A &_now;�A &lastmod;�́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


�s����&heart;�Ə����ƁA�n�[�g�}�[�N &heart; �ɒu������ďo�͂���܂��B
�s����&smile;�Ə����ƁA &smile; �ɒu������ďo�͂���܂��B
�s����&bigsmile;�Ə����ƁA &bigsmile; �ɒu������ďo�͂���܂��B
�s����&huh;�Ə����ƁA &huh; �ɒu������ďo�͂���܂��B
�s����&oh;�Ə����ƁA &oh; �ɒu������ďo�͂���܂��B
�s����&wink;�Ə����ƁA &wink; �ɒu������ďo�͂���܂��B
�s����&sad;�Ə����ƁA &sad; �ɒu������ďo�͂���܂��B
�s����&worried;�Ə����ƁA &worried; �ɒu������ďo�͂���܂��B
�����Q�ƕ����́A�\�������Ƃ��ɒu������܂��B
�����Q�ƕ����́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�����Q�ƕ����́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B


�s���� &# �� ; �̊Ԃ�10�i�����A&#x �� ;�̊Ԃ�16�i���������ƁAUnicode�𐔒l�Q�Ƃ��ĕ\�����܂��B�L�[�{�[�h���璼�ړ��͂ł��Ȃ�������JIS��3�����E��4�����̕����Ȃǂ̕\���Ɏg�p���܂��B���Ƃ��΁A���c�S&#38290;�Ɠ��͂���ƁA���c�S��ƕ\������܂��B

���l�Q�ƕ����́A�\�������Ƃ��ɒu������܂��B
���l�Q�ƕ����́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
���l�Q�ƕ����́A���̃C�����C���v�f���q�v�f�ɂ͂ł��܂���B
16�i���͔��p�������Ŏw�肷��K�v������܂�(XHTML 1.0 ��2�ł��珬�����Ɍ��肳��܂���)�B

 *
 */

public class AmpersandChildInline implements WikiObjectInlineI  {

	private String rawdata = null;
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
	public void set(String str, WikiInlineParser parser) {
		rawdata = str;
		// �ċA�������s��
		new Sub().matchSet(rawdata);

	}
	
	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		sb.append(ampersandtype);
		sb.append("|");
		if (ampersandparam != null) sb.append(ampersandparam);
		sb.append("|");
		return sb.toString();
	}
	
	public class Sub {
		
		// ���K�\���ɂ����āA�K�v�ȏ������o��
		public void matchSet(String str) {
			Pattern pattern1 = Pattern.compile(AMPERSANDCHILDFORMATPATTERN1);
			Matcher matcher1 = pattern1.matcher(str);
			if (matcher1.find()) {
				matchSet1(matcher1);
				return;
			}

			Pattern pattern2 = Pattern.compile(AMPERSANDCHILDFORMATPATTERN2);
			Matcher matcher2 = pattern2.matcher(str);
			if (matcher2.find()) {
				matchSet2("number", matcher2);
				return;
			}

			Pattern pattern3 = Pattern.compile(AMPERSANDCHILDFORMATPATTERN3);
			Matcher matcher3 = pattern3.matcher(str);
			if (matcher3.find()) {
				matchSet3(matcher3);
				return;
			}

			Pattern pattern4 = Pattern.compile(AMPERSANDCHILDFORMATPATTERN4);
			Matcher matcher4 = pattern4.matcher(str);
			if (matcher4.find()) {
				matchSet4(matcher4);
				return;
			}
			
			return;
		}
		
		public void matchSet1(Matcher matcher) {
			if (matcher.groupCount() >= 1) {
				ampersandtype = matcher.group(1);
			}
		}
		
		public void matchSet2(String type, Matcher matcher) {
			ampersandtype = type;
			if (matcher.groupCount() >= 1) {
				ampersandparam = matcher.group(1);
			}
		}
		
		public void matchSet3(Matcher matcher) {
			if (matcher.groupCount() >= 1) {
				ampersandtype = matcher.group(1);
			}
			if (matcher.groupCount() >= 2) {
				ampersandparam = matcher.group(2);
			}
		}
		
		public void matchSet4(Matcher matcher) {
			if (matcher.groupCount() >= 1) {
				ampersandtype = matcher.group(1);
			}
			if (matcher.groupCount() >= 3) {
				ampersandparam = matcher.group(3);
			}
		}
	}
	
	/**
	 * &()�n���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		@Override
		public int getMatchLength(String str) {
			int length = 0;
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDFORMATPATTERN1);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDFORMATPATTERN2);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDFORMATPATTERN3);
			if (length > 0) { return length; }
			length = new Util().getRegexMatcherLength(str, AMPERSANDCHILDFORMATPATTERN4);
			if (length > 0) { return length; }
			
			return 0;
		}
	}
}
