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

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;



/**
 * WikiObject
 * ������
 * @author daxanya
 *
 * --
 * 
�s����4�ȏ�� - �������Ɛ������ɂȂ�܂��B

�������́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ͂ł��܂���B��������������Ƒ��̃u���b�N�v�f�͏I�����܂��B
�������́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 * 
 * --

 */
public class HorizonBlock extends NoChildParentBlockBase {

	@Override
	public String toHtmlString() {
		return "<hr class=\"full_hr\" />" + new Util().getLineSeparator();
	}
	
	@Override
	public int getLevel() {
		return -1;
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() < HORIZON.length()) {
				return false;
			}
			
			return (HORIZON.equals(line.substring(0, HORIZON.length()))) ? true : false;
		}
	}

	@Override
	public void paserInline(WikiInlineParser parser) {
		// TODO Auto-generated method stub
		
	}

}
