package com.appspot.gaejwiki.common.wiki.inline;

import com.appspot.gaejwiki.common.wiki.inline.base.YesChildParentInlineBase;

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

public class AmpersandChildParentInline extends YesChildParentInlineBase {

	/**
	 * &(){}�n���ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		@Override
		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, AMPERSANDCHILDPARENTFORMATPATTERN);
		}
	}
}
