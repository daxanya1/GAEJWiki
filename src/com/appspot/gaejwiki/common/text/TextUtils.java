package com.appspot.gaejwiki.common.text;

public class TextUtils {

	public String escapeHtmlString(String line) {
		if (line == null || line.length() == 0) {
			return line;
		}
		
		StringBuffer sb = new StringBuffer();
		for (char c : line.toCharArray()) {
			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&#39;");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
}
