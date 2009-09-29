package com.appspot.gaejwiki.common.wiki.inline;

import java.util.HashMap;
import java.util.Map;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildNoParentInlineBase;

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

public class PageInline extends YesChildNoParentInlineBase {

	
	/**
	 * �y�[�W�����ǂ����m�F
	 * @param str ������
	 * @return �y�[�W���Ȃ�true
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public static final HashMap<Character, Boolean> PAGENOTINCLUDESMAP = new HashMap<Character, Boolean>();
		
		static {
			for (char includes : PAGENOTINCLUDES) {
				PAGENOTINCLUDESMAP.put(new Character(includes), Boolean.TRUE);
			}
		}
	
		public boolean isThis(String str) {
			if (str == null || str.length() == 0) {
				return false;
			}
			
			Sub sub = new Sub();
			// �y�[�W���`�����A����":&<>���܂܂�Ă��Ȃ��P�[�X�̂݊Y�� �� #�̓A���J�[�Ȃ̂ł͂����Ȃ�
			return (sub.isPageFormat(str) && !(sub.isIncludeChars(str, PAGENOTINCLUDESMAP))) ? true : false;
		}
	}
	
	static public class Sub {
		
		/**
		 * �y�[�W�`��([[...]])���ǂ����𔻒f�iPageInline�̏����Ƃ͈قȂ�A�O����[[, ]]�݂̂��`�F�b�N�j
		 * @param str
		 * @return �y�[�W�`���ł����true
		 */
		public boolean isPageFormat(String str) {
			
			// �y�[�W�`���ł����5�����͕K�v
			if (str == null || str.length() < 5) {
				return false;
			}
			
			return (PAGEFORMATBEGIN.equals(str.substring(0, 2)) && 
					PAGEFORMATEND.equals(str.substring(str.length()-2, 2))) ? true : false;
		}
		
		/**
		 * str���P��������͂��Achars�Ɋ܂܂�Ă��镶��������΁Atrue
		 * @param str
		 * @param chars Map
		 * @return Map���̕�����str�Ɋ܂܂�Ă����true
		 */
		public boolean isIncludeChars(String str, Map<Character, Boolean> chars) {
			for (char ch : str.toCharArray()) {
				if (chars.containsKey(new Character(ch))) {
					return true;
				}
			}
			return false;
		}

	}
}
