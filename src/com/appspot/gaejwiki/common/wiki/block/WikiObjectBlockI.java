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

import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

public interface WikiObjectBlockI {

	public static final char PARAGRAPH = '~';
	public static final char QUOTATION = '>';
	public static final char UNQUOTATION = '<';
	public static final char FORMATED = ' ';
	public static final char UNNUMBEREDLIST = '-';
	public static final char NUMBEREDLIST = '+';
	public static final char DEFINEDLIST = ':';
	public static final char HASH = '#';
	public static final char HEADLINE = '*';
	public static final char TABLE = '|';
	public static final char CENTER = 'C';
	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	public static final char CSV = ',';
	public static final char COMMENTFIRST = '/';
	
	public static final String COMMENT = "//";
	public static final String HORIZON = "----";
	public static final char DEFINEDLIST_SECONDCHAR = '|';
	public static final String CENTERFORMAT = "CENTER:";
	public static final String LEFTFORMAT = "LEFT:";
	public static final String RIGHTFORMAT = "RIGHT:";
	public static final String HASHFORMATPATTERN1 = "^#(contents|hr|br|clear|comment|pcomment|article)$";
	public static final String HASHFORMATPATTERN2 = "^#(ref)\\((.+)\\)$";
	public static final String HASHFORMATPATTERN3 = "^#(vote)\\((.+)\\)$";

	/**
	 * 行を追加する。（一行目も含む）
	 * @param str
	 */
	void addLine(String str);
	
	/**
	 * blockを追加する
	 * @param wikiobject 子block
	 */
	void addChildBlock(WikiObjectBlockI wikiobject);
	
	/**
	 * 親を設定する
	 * @param wikiobject 親block
	 */
	void setParent(WikiObjectBlockI wikiobject);
	
	/**
	 * 次のblockを子供として追加できるかどうか。
	 * @return 次のblockを子供として追加できる場合はtrue
	 */
	boolean isAddChildBlock();
	
	/**
	 * 自分自身を親に追加できるか
	 * @return 自分自身を親に追加できる場合はtrue
	 */
	boolean isAddToParent();
	
	/**
	 * 次の行を追加できるかどうか。
	 * @return 次の行を追加できる場合はtrue
	 */
	boolean isAddLine();
	
	/**
	 * 次の行を追加する際に同じブロックの場合に限るかどうか。
	 * @return 次の行を追加する際に同じブロックの場合に限る場合はtrue
	 */
	boolean isSameBlockAddLine();
	
	/**
	 * このブロックを処理した後、仕切りとするかどうか
	 * @return このブロックを処理した後、仕切りとする場合はtrue
	 */
	boolean isReset();
	
	/**
	 * シンプルな（タグのついていない）文字列を返す。
	 * @return
	 */
	String toString();
	
	/**
	 * HTMLフォーマット用の文字列を返す。
	 * @return
	 */
	String toHtmlString();
	
	/**
	 * Debug用文字列を返す。
	 * @return
	 */
	String toDebugString();
	
	/**
	 * 親を返す
	 * @return 親のWikiObjectBlockI
	 */
	WikiObjectBlockI getParent();
	
	/**
	 * ブロック内文字列をパースしてInline配列にする。
	 * @param parser
	 */
	void paserInline(WikiInlineParser parser);
	
	/**
	 * レベルの概念があるブロックについてレベルを返す。
	 * レベルの概念がないブロックについては-1を返す
	 * @return レベルを返す。
	 */
	int getLevel();
	
	
	static public class Util {
		
		/**
		 * 行の先頭を切って返す
		 * @param data
		 * @return
		 */
		public String cutFrontChar(String data, int length) {
			if (data == null) {
				return null;
			}
			
			return data.substring(length, data.length());
		}
		
		/**
		 * その文字が先頭から連続でどれだけ含まれているかを返す
		 * レベルは最大３までなので、３つ以上の場合、３を返す
		 * @param data 文字列
		 * @param c チェックする文字
		 * @return 先頭にcがなければ-1、先頭にcがあれば連続する数を返す
		 */
		public int checkLevel(String data, char c) {
			if (data == null) {
				return -1;
			}
			
			if (data.length() == 0 || data.charAt(0) != c) {
				return -1;
			}
			
			int count = 1;
			for (int i = 1; i < 3; i++) {
				if (data.charAt(i) != c) {
					break;
				}
				count++;
			}
			return count;
		}
		
		/**
		 * inlineとchildのtoHtmlStringを呼び出して、StringBufferに詰めて返す
		 * @param inlinelist WikiObjectInlineIのリスト
		 * @param childlist WikiObjectBlockIのリスト
		 * @return toHtmlString文字列
		 */
		public String toHtmlString(List<WikiObjectInlineI> inlinelist, List<WikiObjectBlockI> childlist) {
			StringBuffer sb = new StringBuffer();
			if (inlinelist != null) {
				for (WikiObjectInlineI inline : inlinelist) {
					sb.append(inline.toHtmlString());
				}
			}
			if (childlist != null) {
				for (WikiObjectBlockI wikiobj : childlist) {
					sb.append(wikiobj.toHtmlString());
				}
			}
			return sb.toString();
		}
		
		public String getLineSeparator() {
			return DomainParameter.getDomainParameter().getLineSeparator();
		}
	}


	static interface Checker {
		
		/**
		 * 自分のブロック条件に合っているかどうかをチェックする
		 * @param line チェック対象文字列
		 * @return 自分のブロック条件にあっていればtrue
		 */
		boolean isThis(String line);
	}
}
