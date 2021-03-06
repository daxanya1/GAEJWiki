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
package com.appspot.gaejwiki.common.text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.appspot.gaejwiki.domain.setting.DomainParameter;

/**
 * テキストに関するユーティリティクラス
 * @author Ryuichi Danno
 */
public class TextUtils {

	public String removeReturnChar(String data) {
		if (data == null) {
			return null;
		}
		return data.replaceAll("\r\n", "\n");
	}
	
	public String encodeUrlString(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public String[] parseData(String data) {
		if (data == null) {
			return null;
		}
		return data.split(DomainParameter.getDomainParameter().getLineSeparator());
	}
	
	public List<String> parseDataToList(String data) {
		if (data == null) {
			return null;
		}
		return java.util.Arrays.asList(parseData(data));
	}
	

	
	/**
	 * 通常のテキストに対してHTML文字列をエスケープする
	 * @param line エスケープ前の文字列
	 * @return エスケープされた文字列
	 */
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
