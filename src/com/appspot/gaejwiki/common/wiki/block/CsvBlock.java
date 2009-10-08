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

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;


/**
 * WikiObject
 * CSV
 * @author daxanya
 * 
 * --
 * 
�s���ŃJ���}(,)���L�q���A�C�����C���v�f���J���}��؂�ŋL�q����ƕ\�g�݂ɂȂ�܂��B

�C�����C���v�f�̓_�u���N�H�[�e�[�V����(")�ň͂ނ��Ƃ��ł��܂��B�_�u���N�H�[�e�[�V�����ň͂ނ��ƂŁA�J���}(,)���܂ރC�����C���v�f���L�q�ł��܂��B
�_�u���N�H�[�e�[�V����(")�ň͂񂾃f�[�^�̒��ŁA�_�u���N�H�[�e�[�V������2��("")�����邱�ƂŁA�_�u���N�H�[�e�[�V����(")���܂ރC�����C���v�f���L�q�ł��܂��B
�C�����C���v�f�̑���ɃC�R�[����2��(==)�L�q����ƁAcolspan���Ӗ����܂��B
�C�����C���v�f�̍���1�ȏ�̔��p�󔒕������L�q����ƉE�񂹂ɁA�C�����C���v�f�̍��E��1�ȏ�̔��p�󔒕������L�q����ƃZ���^�����O�ɂȂ�܂��B
�\�g�݂́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�\�g�݂́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B

 *
 * --
 */
public class CsvBlock extends YesChildNoAddlineBlockBase {

	@Override
	public String toHtmlString() {
		return "";
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		String rawdata = getRawData();
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (line.charAt(0) == CSV) ? true : false;
		}
	}
}
