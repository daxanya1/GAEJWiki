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
package com.appspot.gaejwiki.common.wiki.inline;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.appspot.gaejwiki.domain.setting.DomainParameter;


/**
 *
 * @author Ryuichi Danno
 */
public interface WikiObjectInlineI {

	public static final char AMPERSAND = '&';
	public static final char STRONG = '\'';
	public static final char PARCENT = '%';
	public static final char ROUNDBRACKET = '(';
	public static final char ANGLEBRACKET = '[';
	public static final char TILDE = '~';
	
	public static final String WIKINAMEFORMATPATTERN = "^([A-Z]+[a-z]+[A-Z]+[a-z]+)";
	public static final String NOTEFORMATPATTERN = "^\\(\\((.+)\\)\\)";
	public static final String STRIKEFORMATPATTERN = "^%%(.+)%%";
	public static final String STRONGFORMATPATTERN = "^''(.+)''";
	public static final String ITALICFORMATPATTERN = "^'''(.+)'''";
	
	public static final String URLPATTERN = "([a-z]+://[-_.!~*'\\(\\)a-zA-Z0-9;/?:@&=+$,%#]+)";
	public static final String MAILPATTERN = "((?:(?:(?:(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+)(?:\\.(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+))*)|(?:\"(?:\\\\[^\\r\\n]|[^\\\\\"])*\")))\\@(?:(?:(?:(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+)(?:\\.(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+))*)|(?:\\[(?:\\\\\\S|[\\x21-\\x5a\\x5e-\\x7e])*\\]))))";
	public static final String PAGEPATTERN = "([^\":&<>]+?)";
	public static final String PAGEFORMATPATTERN = "^(\\[\\[" + PAGEPATTERN + "\\]\\]){1}?";
	
	public static final String LINKFORMATPATTERN1 = "^\\[\\[([^:>]+)" + "[:>]" + URLPATTERN + "\\]\\]";
	public static final String LINKFORMATPATTERN2 = "^\\[\\[([^:>]+)" + "[:>]" + MAILPATTERN + "\\]\\]";
	public static final String LINKFORMATPATTERN3 = "^\\[\\[" + URLPATTERN + "\\]\\]";
	public static final String LINKFORMATPATTERN4 = "^\\[\\[" + MAILPATTERN + "\\]\\]";
	public static final String LINKFORMATPATTERN5 = "^\\[\\[([^>]+)>(([^:]+):)?" + PAGEPATTERN + "\\]\\]";
	public static final String LINKFORMATPATTERN6 = "^\\[\\[([^:]+):" + PAGEPATTERN + "\\]\\]";

	public static final String AMPERSANDCHILDFORMATPATTERN1 = "^&(br|online|version|page|fpage|date|time|now|_date|_time|_now|heart|smile|bigsmile|huh|oh|wink|sad|worried|t);";
	public static final String AMPERSANDCHILDFORMATPATTERN2 = "^&((#[0-9]+)|(#x[0-9a-f]+));";
	public static final String AMPERSANDCHILDFORMATPATTERN3 = "^&(ref)\\(([^\\(\\)]+)\\);";
	public static final String AMPERSANDCHILDFORMATPATTERN4 = "^&(counter)(\\((today|yesterday|total)\\))?;";

	public static final String AMPERSANDCHILDPARENTFORMATPATTERN1 = "^&(color|ruby)\\(([^\\(\\)]+)\\)\\{([^\\{\\}]+)\\};";
	public static final String AMPERSANDCHILDPARENTFORMATPATTERN2 = "&(size)\\(([0-9]+)\\)(\\{([^\\{\\}]+)\\})?;";
	public static final String AMPERSANDCHILDPARENTFORMATPATTERN3 = "&(aname)\\(([a-zA-Z][a-zA-Z0-9-_]*)\\)(\\{([^\\{\\}]+)\\})?;";
	public static final char ANCHOR = '#';


	/**
	 * 文字を追加する。
	 * @param str 追加する文字
	 * @param factory TODO
	 */
	void set(String str, WikiInlineParser parser);
	
	/**
	 * 親を設定する
	 * @param wikiobject 親inline
	 */
	void setParent(WikiObjectInlineI wikiobject);
	
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
	 * @return 親のWikiObjectInlineI
	 */
	WikiObjectInlineI getParent();


	static public class Util {
		
		// 正規表現にかけて、必要な情報を取り出す
		public String matchSet(String str, String pat, int groupnum) {
			if (str == null || pat == null) {
				return null;
			}
			
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				return (matcher.groupCount() >= groupnum) ? matcher.group(groupnum) : null;
			}
			
			return null;
		}
		
		public String toDebugString(String name, List<WikiObjectInlineI> childlist) {
			StringBuffer sb = new StringBuffer();
			sb.append(name);
			sb.append("|");
			if (childlist != null) {
				for (WikiObjectInlineI inline : childlist) {
					sb.append("c:/");
					sb.append(inline.toDebugString());
					sb.append("/:c");
				}
			}
			return sb.toString();			
		}
		
		public String getLineSeparator() {
			return DomainParameter.getDomainParameter().getLineSeparator();
		}
	}
	
	/**
	 * 自分自身かどうかをチェックする
	 */
	static interface Checker {
		
		/**
		 * 自分自身であれば、文字列を切り取るEndPositionを返す
		 * @param str 文字列
		 * @return endpositionを返す。なければ0を返す
		 */
		int getMatchLength(String str);
		
		static class Util {
			/**
			 * patにstrがマッチしたら、マッチした文字数を返す
			 * @param str
			 * @param pat パターン
			 * @return マッチした文字数
			 */
			public int getRegexMatcherLength(String str, String pat) {
				if (str == null || pat == null) {
					return 0;
				}
				Pattern pattern = Pattern.compile(pat);
				Matcher matcher = pattern.matcher(str);
				if (matcher.find()) {
					return matcher.group(0).length();
				}
				return 0;
			}
		}
	}

}
