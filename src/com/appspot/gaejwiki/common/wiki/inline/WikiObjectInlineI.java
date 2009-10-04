package com.appspot.gaejwiki.common.wiki.inline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface WikiObjectInlineI {

	public static final char AMPERSAND = '&';
	public static final char STRONG = '\'';
	public static final char PARCENT = '%';
	public static final char ROUNDBRACKET = '(';
	public static final char ANGLEBRACKET = '[';
	public static final char TILDE = '~';
	
	public static final String WIKINAMEFORMATPATTERN = "^([A-Z]+[a-z]+[A-Z]+[a-z]+)";
	public static final String NOTEFORMATPATTERN = "^\\(\\((.+)\\)\\)";
	public static final String STRIKEFORMATPATTERN = "^%(.+)%";
	public static final String STRONGFORMATPATTERN = "^''(.+)''";
	public static final String ITALICFORMATPATTERN = "^'''(.+)'''";
	
	public static final String URLPATTERN = "([a-z]+://[-_.!~*'\\(\\)a-zA-Z0-9;/?:@&=+$,%#]+)";
	public static final String MAILPATTERN = "((?:(?:(?:(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+)(?:\\.(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+))*)|(?:\"(?:\\\\[^\\r\\n]|[^\\\\\"])*\")))\\@(?:(?:(?:(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+)(?:\\.(?:[a-zA-Z0-9_!#\\$\\%&'*+/=?\\^`{}~|\\-]+))*)|(?:\\[(?:\\\\\\S|[\\x21-\\x5a\\x5e-\\x7e])*\\]))))";
	public static final String PAGEPATTERN = "([^\":&<>]+)";
	public static final String PAGEFORMATPATTERN = "^\\[\\[" + PAGEPATTERN + "\\]\\]";
	
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
	void set(String str, WikiObjectInlineFactory factory);
	
	/**
	 * 親を設定する
	 * @param wikiobject 親inline
	 */
	void setParent(WikiObjectInlineI wikiobject);
	
	/**
	 * Wikiフォーマット用の文字列を返す。
	 * @return
	 */
	String toString();
	
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
		public String matchSet(String str, String pat) {
			if (str == null || pat == null) {
				return null;
			}
			
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				return (matcher.groupCount() >= 1) ? matcher.group(1) : null;
			}
			
			return null;
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
