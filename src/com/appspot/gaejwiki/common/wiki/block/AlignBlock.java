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

import com.appspot.gaejwiki.common.wiki.block.base.YesChildAddlineBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

/**
 * WikiObject
 * 左寄せ・センタリング・右寄せ
 * @author daxanya
 * 
 * --
 * 
行頭で LEFT:、 CENTER:、 RIGHT: を記述すると、インライン要素が左寄せ、センタリング、右寄せされます。

LEFT:、CENTER:、RIGHT:は、他のブロック要素の子要素になることができます。
LEFT:、CENTER:、RIGHT:は、他のブロック要素を子要素にすることができません。

 *
 * --
 */
public class AlignBlock extends YesChildAddlineBlockBase {

	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	private char aligntype;
	
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div style=\"text-align:");
		switch(aligntype) {
		case LEFT:	
			sb.append("left");
			break;
		case RIGHT:	
			sb.append("right");
			break;
		case CENTER:	
			sb.append("center");
			break;
		default: break;
		}
		sb.append("\">");
		for (WikiObjectInlineI inline : inlinelist) {
			sb.append(inline.toHtmlString());
		}
		sb.append("</div>");
		sb.append(new Util().getLineSeparator());
		return sb.toString();
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		String rawdata = getRawData();
		Util util = new Util();
		aligntype = util.getType(rawdata);
		inlinelist = parser.parseInline(util.cutFrontChar(rawdata));
	}
	
	public char getAlignType() {
		return aligntype;
	}
	
	static public class Util extends WikiObjectBlockI.Util {
		public char getType(String data) {
			return (data.charAt(0) == LEFT) ? LEFT :
				(data.charAt(0) == RIGHT) ? RIGHT :
				(data.charAt(0) == CENTER) ? CENTER : null;
		}
		
		public String cutFrontChar(String data) {
			return (data.charAt(0) == LEFT) ? cutFrontChar(data, LEFTFORMAT.length()) : 
				(data.charAt(0) == RIGHT) ? cutFrontChar(data, RIGHTFORMAT.length()) : 
				(data.charAt(0) == CENTER) ? cutFrontChar(data, CENTERFORMAT.length()) :  null;
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {

		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// LEFT:,CENTER:,RIGHT:のどれかだったらtrue
			return (equalsSubstring(line, CENTERFORMAT)) ? true : 
				(equalsSubstring(line, LEFTFORMAT)) ? true :
				(equalsSubstring(line, RIGHTFORMAT)) ? true : false;
		}
		
		private boolean equalsSubstring(String line, String key) {
			if (line.length() < key.length()) {
				return false;
			}
			return key.equals(line.substring(0, key.length()));
		}
	}
}
