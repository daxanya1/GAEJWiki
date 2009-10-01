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
