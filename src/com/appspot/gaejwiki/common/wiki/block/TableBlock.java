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

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

/**
 * WikiObject
 * �\�g
 * @author daxanya
 * 
 * --
 * 
�s������ | �ŃC�����C���v�f����؂邱�Ƃŕ\�g�݂ɂȂ�܂��B

�e�v�f�̐擪�ɉ��L�̋L�q�q���w��ł��܂��B
LEFT:
CENTER:
RIGHT:
BGCOLOR(�F):
COLOR(�F):
SIZE(�T�C�Y):
�v�f�̕\���ʒu�y�єw�i�F�E�����F�E�����T�C�Y(px�P��)���w�肵�܂��B�f�t�H���g�͍��񂹂ɂȂ�܂��B
�\�g�݂�	�e�Z���̗v�f�̔z�u��	�ւ���T���v��
����	�Z���^�����O	�E��
�E��	����	�Z���^�����O
�s����c���L�q����ƁA�����w��s�ƂȂ�܂��B�����w��s�ł́A���̋L�q�q���w��ł��܂��B
LEFT:
CENTER:
RIGHT:
BGCOLOR(�F):
COLOR(�F):
SIZE(�T�C�Y):
�L�q�q�̌��ɐ��l���L�q����ƁA�Z������px�P�ʂŎw��ł��܂��B
�s����h���L�q����ƁA�w�b�_�s(thead)�ɂȂ�܂��B
�s����f���L�q����ƁA�t�b�^�s(tfoot)�ɂȂ�܂��B
�Z�����̃C�����C���v�f�̐擪��~��t����ƁA�w�b�_(th)�ɂȂ�܂��B
�Z������ > ��P�ƂŋL�q����ƉE�̃Z���ƘA�����܂�(colspan)�B
�Z������ ~ ��P�ƂŋL�q����Ə�̃Z���ƘA�����܂�(rowspan)�B
�\�g�݂́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�\�g�݂́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B

 *
 * --
 */
public class TableBlock extends SameAddBlockBase {

	private List<List<WikiObjectInlineI>> inlinelistlist = new ArrayList<List<WikiObjectInlineI>>();
	

	@Override
	public String toHtmlString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		
		List<String> datalist = getRawlist();
		if (datalist == null || datalist.size() == 0) {
			return;
		}

		// �Z������؂��āA���̌セ�ꂼ����p�[�X����K�v������B
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {

		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (line.charAt(0) == TABLE) ? true : false;
		}
	}

}
