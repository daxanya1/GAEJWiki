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

import com.appspot.gaejwiki.common.wiki.block.CsvBlock.Sub.CsvElement;
import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;


/**
 * WikiObject
 * CSV
 * @author daxanya
 * 
 * --
 * 
行頭でカンマ(,)を記述し、インライン要素をカンマ区切りで記述すると表組みになります。

インライン要素はダブルクォーテーション(")で囲むことができます。ダブルクォーテーションで囲むことで、カンマ(,)を含むインライン要素を記述できます。
ダブルクォーテーション(")で囲んだデータの中で、ダブルクォーテーションを2つ("")続けることで、ダブルクォーテーション(")を含むインライン要素を記述できます。
インライン要素の代わりにイコールを2つ(==)記述すると、colspanを意味します。
インライン要素の左に1つ以上の半角空白文字を記述すると右寄せに、インライン要素の左右に1つ以上の半角空白文字を記述するとセンタリングになります。
表組みは、他のブロック要素の子要素になることができます。
表組みは、他のブロック要素を子要素にすることができません。

 *
 * --
 */
public class CsvBlock extends SameAddBlockBase {

	private List<List<CsvElement>> csvelementlistlist = null;
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		int beforecsvnum = -1;
		for (List<CsvElement> elementlist : csvelementlistlist) {
			int csvnum = 0;
			
			// 今回の行のCSVの数を数える
			for (CsvElement element : elementlist) {
				csvnum += element.getColspanLevel();
			}
			
			// 前のCSVの数が合わないとテーブルに矛盾が出るのでtableを囲みなおす
			if (csvnum != beforecsvnum) {
				// 最初だけは終わりの囲みはいらないので、最初かどうか判断する
				if (beforecsvnum >= 0) {
					sb.append("</table></div>");
					sb.append(new Util().getLineSeparator());
				}
				sb.append("<div class=\"ie5\"><table class=\"style_table\" cellspacing=\"1\" border=\"0\">");
				sb.append(new Util().getLineSeparator());
				beforecsvnum = csvnum;
			}
			
			sb.append("<tr class=\"style_tr\">");
			for (CsvElement element : elementlist) {
				sb.append("<td class=\"style_td\"");
				if (element.getAlign() == CsvElement.CENTERALIGN) {
					sb.append(" style=\"text-align:center\"");
				} else if (element.getAlign() == CsvElement.RIGHTALIGN) {
					sb.append(" style=\"text-align:right\"");
				}
				if (element.getColspanLevel() > 1) {
					sb.append(" colspan=\"" +  element.getColspanLevel() + "\"");
				}
				sb.append(">");
				List<WikiObjectInlineI> inlinelist = element.getInlineList();
				if (inlinelist != null) {
					for (WikiObjectInlineI inline : inlinelist) {
						sb.append(inline.toHtmlString());
					}
				}
				sb.append("</td>");
			}
			sb.append("</tr>");
			sb.append(new Util().getLineSeparator());
		}
		sb.append("</table></div>");
		sb.append(new Util().getLineSeparator());
		return sb.toString();
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		Sub sub = new Sub();
		csvelementlistlist = new ArrayList<List<CsvElement>>();
		for (String line : getRawlist()) {
			csvelementlistlist.add(sub.parseCsvElementList(parser, sub.analyzeCsvElementList(sub.parseCsvLine(line))));
		}
	}
	
	static public class Sub {

		public class CsvElement {
			static public final int LEFTALIGN = 0;
			static public final int RIGHTALIGN = 1;
			static public final int CENTERALIGN = 2;
			
			private StringBuffer element = new StringBuffer();
			private int colspanlevel = 0;
			private List<WikiObjectInlineI> inlinelist = null;
			private int align = LEFTALIGN;
			 
			public void addElement(char c) {
				element.append(c);
			}
			
			public void cutDoubleQuote() {
				if (element.length() == 0) {
					return;
				}
				
				if (element.charAt(0) == '"') {
					element.deleteCharAt(0);
				}
				if (element.charAt(element.length()-1) == '"') {
					element.deleteCharAt(element.length()-1);
				}
			}
			
			public void cutAndCheckAlign() {
				if (element.length() == 0) {
					return;
				}
				
				boolean topflag = false;
				boolean lastflag = false;
				if (element.charAt(0) == ' ') {
					element.deleteCharAt(0);
					topflag = true;
				}
				if (element.charAt(element.length()-1) == ' ') {
					element.deleteCharAt(element.length()-1);
					lastflag = true;
				}
				if (topflag) {
					if (lastflag) {
						align = CENTERALIGN;
					} else {
						align = RIGHTALIGN;
					}
				}
			}
			
			public void parseElement(WikiInlineParser parser) {
				inlinelist = parser.parseInline(getElement());
			}
			
			public void addColspanLevel() {
				colspanlevel ++;
			}
			
			public String getElement() {
				return element.toString();
			}
			
			public int getColspanLevel() {
				return colspanlevel + 1;
			}
			
			public List<WikiObjectInlineI> getInlineList() {
				return inlinelist;
			}
			
			public int getAlign() {
				return align;
			}
		}
		
		/**
		 * @param line
		 * @return
		 */
		public List<CsvElement> parseCsvLine(String line) {
			assert(line != null);
			assert(line.charAt(0) == ',');
			List<CsvElement> list = new ArrayList<CsvElement>();
			
			boolean doublequoteflag = false;
			CsvElement nowelement = null;
			char beforec = 0;
			for (char c : line.toCharArray()) {
				if (doublequoteflag) {
					if (c == '"') {
						// 直前が"以外で閉じる
						if (beforec != '"') {
							doublequoteflag = false;
						}
					}
				} else {
					if (c == ',') {
						nowelement = new CsvElement();
						list.add(nowelement);
						beforec = 0;
						continue;
					} else if (c == '"'){ 
						doublequoteflag = true;
						
					}
				}
				nowelement.addElement(c);
				beforec = c;
			}
			
			return list;
		}
		
		/**
		 * 
		 * @param csvelementlist
		 * @return
		 */
		public List<CsvElement> analyzeCsvElementList(List<CsvElement> csvelementlist) {
			assert(csvelementlist != null);
			List<CsvElement> outputlist = new ArrayList<CsvElement>();
			
			CsvElement beforeelement = null;
			for (CsvElement element: csvelementlist) {
				String str = element.getElement();
				if (str == null) {
					continue;
				}
				if (str.equals("==") && beforeelement != null) {
					beforeelement.addColspanLevel();
					continue;
				}
				
				element.cutDoubleQuote();
				element.cutAndCheckAlign();
				outputlist.add(element);
				beforeelement = element;
			}
			
			return outputlist;
		}

		/**
		 * @param parser
		 * @param elementlist
		 * @return
		 */
		public List<CsvElement> parseCsvElementList(WikiInlineParser parser, List<CsvElement> elementlist) {
			assert(parser != null);
			assert(elementlist != null);
			
			for (CsvElement element : elementlist) {
				element.parseElement(parser);
			}
			
			return elementlist;
		}
		
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
