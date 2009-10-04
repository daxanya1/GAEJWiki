package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;

/**
 * WikiObject
 * �ԍ����胊�X�g
 * @author daxanya
 *
 * --
 *
�s���� + ���w�肷��ƁA�ԍ��t�����X�g�ɂȂ�܂��B�ԍ��t�����X�g�� +�A++�A+++ ��3���x������܂��B

�ԍ��t�����X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̃��X�g�\���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���p���̎q�v�f�ɂ���ꍇ�́A���x���𑝂₳���ɋL�q���܂��B
+ �̒���� ~ ���L�q����ƒi�����q�v�f�ɂ��邱�Ƃ��ł��܂��B
�ԍ��t�����X�g�́A���X�g�̐擪���C�����C���v�f�܂��͒i���ł���ꍇ�Ɍ���A���X�g�̎��̍s�ɑ��̃u���b�N�v�f���L�q���邱�ƂŁA���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 * --
 */
public class NumberedListBlock extends ListBlockBase {

	@Override
	protected List<String> cutDataList(List<String> datalist) {
		List<String> list = new ArrayList<String>();
		if (datalist == null) {
			return list;
		}
		
		
		
		return list;
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// NUMBEREDLIST�v�f���ꕶ���ڂł���΁A�ԍ����胊�X�g�Ƃ���
			return (line.charAt(0) == NUMBEREDLIST) ? true : false;
		}
	}
}
