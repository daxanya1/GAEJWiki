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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;


/**
 * WikiObject
 * #�`�̃u���b�N
 * @author daxanya
 * 
 * --
 * 
#contents : �ڎ�
#hr : ������
#br : �s�ԊJ��
#clear : �e�L�X�g�̉�荞�݂̉���
#comment,#pcomment, #article : �t�H�[��

#ref(�Y�t�t�@�C����)
#ref(�t�@�C����URL)
�s���� #ref ���L�q����ƁA�Y�t�t�@�C���܂��͎w�肳�ꂽURL�ɂ���t�@�C���ւ̃����N��\��t���邱�Ƃ��ł��܂��B�t�@�C�����摜�t�@�C���̏ꍇ�́A���̉摜��\�����܂��B

#ref �ɂ́A�J���}�ŋ�؂��ĉ��L�̃p�����^���w��ł��܂��B�p�����^���ȗ������ꍇ�̓f�t�H���g�l�ƂȂ�܂��B
�Y�t�t�@�C���̃y�[�W
�Y�t�t�@�C�������݂���y�[�W�����w�肵�܂��B�f�t�H���g�͌��݂̃y�[�W�ł��B
���̃p�����^���w�肷��ꍇ�́A�Y�t�t�@�C�����̎��ɋL�q���܂��B
left
center
right
�t�@�C���ւ̃����N�E�摜��\������ʒu���w�肵�܂��B�f�t�H���g�ł̓Z���^�����O����܂��B
wrap
nowrap
�t�@�C���ւ̃����N�E�摜��g�ň͂ނ��ǂ������w�肵�܂��B�f�t�H���g�͈݂͂܂���B
around
�e�L�X�g�̉�荞�݂��w�肵�܂��B�f�t�H���g�ł͉�荞�݂��s�Ȃ��܂���B
nolink
�f�t�H���g�ł͓Y�t�t�@�C���ւ̃����N�������܂����Anolink���w�肷��ƃ����N�𒣂�܂���B
��֕�����
�t�@�C�����̑���ɕ\�����镶�����摜�̑�֕�������w��ł��܂��B�w�肵�Ȃ��ꍇ�́A�t�@�C�����ɂȂ�܂��B
��֕�����ɂ͕�����ȊO�̃C�����C���v�f���܂߂邱�Ƃ͂ł��܂���B�y�[�W���A������ȊO�̃C�����C���v�f���L�q���Ă�������Ƃ��Ĉ����܂��B
���̃p�����^���w�肷��ꍇ�́A�Ō�ɋL�q���܂��B
#ref�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
#ref�́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B

#vote(�I����1,�I����2,...)
�s���� #vote ���L�q����ƁA�ȈՓ��[�t�H�[�������ߍ��܂�܂��B

�I�����͕�����A�y�[�W���AInterWiki�A�����N���܂߂邱�Ƃ��ł��܂��B���̑��̃C�����C���v�f���܂߂邱�Ƃ��ł��܂���B
#vote�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂����A�g�b�v���x���ɐݒu���邱�Ƃ�O��ɍ��}�[�W����ݒ肵�Ă���܂��̂ŁA���̃u���b�N�v�f�̎q�v�f�ɂ͂��Ȃ��ł��������B
#vote�́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 *
 * --
 */
public class HashBlock extends YesChildNoAddlineBlockBase {

	enum HashBlockType {
		contents,
		hr,
		br,
		ref,
		clear,
		comment,
		pcomment,
		article,
		vote
	}
	
	private HashBlockType type;
	
	@Override
	public String toHtmlString() {
		if (type == null) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		switch (type) {
		case hr:
			sb.append("<hr class=\"short_line\" />");
			break;
		case br:
			sb.append("<div class=\"spacer\">&nbsp;</div>");
			break;
		default:
			break;
		}
		sb.append(new Util().getLineSeparator());
		return sb.toString();
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		Sub sub = new Sub();
		type = sub.getType(getRawData(), HASHFORMATPATTERN1);
		if (type == null) {
			type = sub.getType(getRawData(), HASHFORMATPATTERN2);
		}
		if (type == null) {
			type = sub.getType(getRawData(), HASHFORMATPATTERN3);
		}
		if (type == null) {
			return;
		}
		
		switch (type) {
		case ref:
			// ref�p���ꏈ��
			break;
		case vote:
			// vote�p���ꏈ��
			break;
		}

	}
	
	static public class Sub {
		
		/**
		 * line�𐳋K�\���ɂ����āA�}�b�`������Atype��Ԃ�
		 * @param line
		 * @param pat
		 * @return
		 */
		public HashBlockType getType(String line, String pat) {
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(line);
			if (!matcher.find()) {
				return null;
			}
			if (matcher.groupCount() == 0) {
				return null;
			}
			for (HashBlockType blocktype : HashBlockType.values()) {
				if (blocktype.toString().equals(matcher.group(1))) {
					return blocktype;
				}
			}
			return null;
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (new WikiObjectInlineI.Checker.Util().getRegexMatcherLength(line, HASHFORMATPATTERN1) > 0) ? true : 
				(new WikiObjectInlineI.Checker.Util().getRegexMatcherLength(line, HASHFORMATPATTERN2) > 0) ? true :
				(new WikiObjectInlineI.Checker.Util().getRegexMatcherLength(line, HASHFORMATPATTERN3) > 0) ? true : false;
		}

	}

}
