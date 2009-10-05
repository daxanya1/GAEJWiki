package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.AlignBlock.Util;
import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;
import com.appspot.gaejwiki.common.wiki.common.WikiDefine;
import com.appspot.gaejwiki.common.wiki.inline.NewlineInline;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;



/**
 * WikiObject
 * 見出し
 * @author daxanya
 *
 * --
 * 
行頭で * を記述すると、見出しになります。見出しは *、**、*** の3段階あります。

見出しは、他のブロック要素の子要素になることはできません。見出しが現われると他のブロック要素は終了します。
見出しは、他のブロック要素を子要素にすることはできません。
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

		// 最初のHeadline以外は、一番上に戻るためのタグを追加
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
			
			// level1がh2、level2がh3、level3がh4となるため、1足す
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
			sb.append("href=\"" + WikiDefine.INDEXPATH + "?ref#id" + idstr + "\" title=\"id" + idstr + "\">&dagger;</a></h" + levelstr + ">");
			sb.append(new Util().getLineSeparator());
			return sb.toString();
		}
		
		public String makeJumpmenu() {
			return "<div class=\"jumpmenu\"><a href=\"#navigator\">&uarr;</a></div>";
		}
		
		/**
		 * inlineについて、Headlineに合わせて変換したくないinlineの場合、trueとする
		 * @param inline
		 * @return 変換したくないinlineの場合true
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
