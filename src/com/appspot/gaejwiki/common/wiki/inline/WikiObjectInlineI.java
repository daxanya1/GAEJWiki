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
	public static final String PAGEFORMATPATTERN = "^\\[\\[[^\":&<>]+\\]\\]";
	public static final String LINKFORMATPATTERN = "^\\[\\[[^:>]+[:>].+\\]\\]";
	public static final String AMPERSANDCHILDFORMATPATTERN = "^(" +
	"&(br|online|version|page|fpage|date|time|now|_date|_time|_now|t);" +
	"|&(heart|smile|bigsmile|huh|oh|wink|sad|worried);" +
	"|&#[0-9]+;" +
	"|&#x[0-9a-f]+;" +
	"|&ref\\([^\\(\\)]+\\);" +
	"|&counter(\\([^\\(\\)]+\\))?;" +
	")";

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
