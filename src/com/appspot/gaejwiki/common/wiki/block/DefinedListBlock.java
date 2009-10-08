/**
 Copyright 2009 GAEJWiki Team.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.appspot.gaejwiki.common.wiki.block;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;


/**
 * WikiObject
 * ��`���X�g
 * @author daxanya
 * 
 * --
 * 
�s���� : �Ŏn�߁A| �L���ŋ�؂�ƁA��`���X�g�ɂȂ�܂��B��`���X�g�� :�A::�A::: ��3�i�K����܂��B��`���X�g�̒�`��A�������͏ȗ����邱�Ƃ��ł��܂��B�����̘A��������`���X�g���L�q���A2�ڈȍ~�̒�`����ȗ����邱�Ƃ�1�̒�`��ɑ΂��镡���̐��������L�q���邱�Ƃ��ł��܂��B

�s���� | ���Ȃ��ƒ�`���X�g�ɂ͂Ȃ�܂���B
��`��E�������́A�C�����C���v�f�̂݋L�q���邱�Ƃ��ł��܂��B
��`���X�g�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̃��X�g�\���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���p���̎q�v�f�ɂ���ꍇ�́A���x���𑝂₳���ɋL�q���܂��B
| �̒���� ~ ���L�q����ƒi�����q�v�f�ɂ��邱�Ƃ��ł��܂��B
��`���X�g�́A��`���X�g�̎��̍s�ɑ��̃u���b�N�v�f���L�q���邱�ƂŁA���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B
 *
 * --
 */
public class DefinedListBlock extends ListBlockBase {

	@Override
	protected String toHtmlStringFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String toHtmlStringHeader() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String cutBlockChar(List<String> datalist) {
		return new Util().cutBlockCharListBlockBase(this, datalist, DEFINEDLIST);
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// ��`���X�g�v�f���ꕶ���ڂł��A�ǂ�����|������Β�`���X�g�Ƃ���
			return (line.charAt(0) == DEFINEDLIST && line.indexOf(DEFINEDLIST_SECONDCHAR) >= 0) ? true : false;
		}
	}
}
