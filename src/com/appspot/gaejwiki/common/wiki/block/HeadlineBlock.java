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

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.NewlineInline;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;
import com.appspot.gaejwiki.domain.setting.DomainParameter;



/**
 * WikiObject
 * ���o��
 * @author daxanya
 *
 * --
 * 
�s���� * ���L�q����ƁA���o���ɂȂ�܂��B���o���� *�A**�A*** ��3�i�K����܂��B

���o���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ͂ł��܂���B���o����������Ƒ��̃u���b�N�v�f�͏I�����܂��B
���o���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 * 
 * --

 */
public class HeadlineBlock extends NoChildParentBlockBase {

	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	private int level = -1;
	private int contentsid = -1;
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();

		// �ŏ���Headline�ȊO�́A��ԏ�ɖ߂邽�߂̃^�O��ǉ�
		if (contentsid != 0) {
			sb.append(new Sub().makeJumpmenu());
		}
		
		sb.append(new Sub().makeHeadline(inlinelist, level, contentsid));
		return sb.toString();
	}

	
	@Override
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		Util util = new Util();
		level = util.checkLevel(getData(), HEADLINE);
		inlinelist.addAll(parser.parseInline(util.cutFrontChar(getData(), level)));
		contentsid = parser.addHeadline(this);
	}
	
	@Override
	public int getLevel() {
		return level;
	}
	
	static public class Sub {

		public String makeHeadline(List<WikiObjectInlineI> inlinelist, int level, int contentsid) {
			assert(inlinelist != null);
			StringBuffer sb = new StringBuffer();
			
			String idstr = new Integer(contentsid).toString();
			DomainParameter domainparam = DomainParameter.getDomainParameter();
			
			// level1��h2�Alevel2��h3�Alevel3��h4�ƂȂ邽�߁A1����
			String levelstr = new Integer(level + 1).toString();
			
			sb.append("<h" + levelstr + " id=\"content_1_" + idstr + "\">");
			for (WikiObjectInlineI inline: inlinelist) {
				if (isSimpleString(inline)) {
					sb.append(inline.toString());
				} else {
					sb.append(inline.toHtmlString());
				}
			}
			sb.append("  <a class=\"anchor_super\" id=\"id" + idstr + "\" ");
			sb.append("href=\"" + domainparam.get(DomainParameter.VIEWURL) + "?ref#id" + idstr + "\" title=\"id" + idstr + "\">&dagger;</a></h" + levelstr + ">");
			sb.append(new Util().getLineSeparator());
			return sb.toString();
		}
		
		public String makeJumpmenu() {
			return "<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div>";
		}
		
		/**
		 * inline�ɂ��āAHeadline�ɍ��킹�ĕϊ��������Ȃ�inline�̏ꍇ�Atrue�Ƃ���
		 * @param inline
		 * @return �ϊ��������Ȃ�inline�̏ꍇtrue
		 */
		public boolean isSimpleString(WikiObjectInlineI inline) {
			return (inline instanceof NewlineInline) ? true : false;
		}


	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (line.charAt(0) == HEADLINE) ? true : false;
		}
	}
	
}
