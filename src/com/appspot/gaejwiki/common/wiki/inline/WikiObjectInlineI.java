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
	 * ������ǉ�����B
	 * @param str �ǉ����镶��
	 * @param factory TODO
	 */
	void set(String str, WikiObjectInlineFactory factory);
	
	/**
	 * �e��ݒ肷��
	 * @param wikiobject �einline
	 */
	void setParent(WikiObjectInlineI wikiobject);
	
	/**
	 * Wiki�t�H�[�}�b�g�p�̕������Ԃ��B
	 * @return
	 */
	String toString();
	
	/**
	 * Debug�p�������Ԃ��B
	 * @return
	 */
	String toDebugString();
	
	/**
	 * �e��Ԃ�
	 * @return �e��WikiObjectInlineI
	 */
	WikiObjectInlineI getParent();


	static public class Util {
		
		// ���K�\���ɂ����āA�K�v�ȏ������o��
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
	 * �������g���ǂ������`�F�b�N����
	 */
	static interface Checker {
		
		/**
		 * �������g�ł���΁A�������؂���EndPosition��Ԃ�
		 * @param str ������
		 * @return endposition��Ԃ��B�Ȃ����0��Ԃ�
		 */
		int getMatchLength(String str);
		
		static class Util {
			/**
			 * pat��str���}�b�`������A�}�b�`������������Ԃ�
			 * @param str
			 * @param pat �p�^�[��
			 * @return �}�b�`����������
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
