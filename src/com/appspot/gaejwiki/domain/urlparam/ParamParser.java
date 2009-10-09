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

package com.appspot.gaejwiki.domain.urlparam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.appspot.gaejwiki.domain.page.PageParam;

/**
 *
 * @author Ryuichi Danno
 */
public class ParamParser {

	/**
	 * requestからURLとパラメータを取り出し分析してMapにつめて返す
	 * 
	 * URLのフォーマットは、http://ドメイン/path/aaaa?ref=bbbb&otherkey=cccc&...
	 * とする
	 * 
	 * URLから取り出す情報は、
	 * 1.どのページか PAGE=aaaa (required)
	 * 2.どこから呼ばれたか REFER=bbbb
	 * 3.その他オプションがあれば otherkey=cccc
	 * 
	 * 
	 * @param req HttpServletRequest
	 * @return パラメータのMap
	 */
	public PageParam parseUrl(HttpServletRequest req, String requestcommand) {
		if (req == null) {
			return null;
		}
		
		Util util = new Util();
		PageParam pageparam = new PageParam();
		
		String page = util.getPage(req.getRequestURI(), requestcommand);
		pageparam.put(PageParam.PAGEKEY, util.decodeUrl(page));
		pageparam.putAll(util.arrangeMap(req.getParameterMap()));
		
		return pageparam;
	}
	
	static public class Util {
		private static final String URLPATTERN = "/([^/]+/)+([^?]+)";
		
		/**
		 * URLから、ページ情報を返す。
		 * URLのpage情報の前のpathと正規表現中のpath部分が合致していなければ無効
		 * 
		 * @param str URL文字列
		 * @param requestcommand URLのpage情報の前のpath 
		 * @return page情報の文字列を返す。無効であればnull
		 */
		public String getPage(String str, String requestcommand) {
			if (str == null || requestcommand == null) {
				return null;
			}
			Matcher matcher = matchUrl(str);
			if (matcher == null) {
				return null;
			}
			if (matcher.groupCount() < 2) {
				return null;
			}
			if (!requestcommand.equals(matcher.group(1))) {
				return null;
			}
			return matcher.group(2);
		}
		
		/**
		 * URL文字列が正規表現にマッチしているか調べる
		 * @param str URL文字列
		 * @return マッチしていればmatcherを返す。マッチしていなければnull
		 */
		public Matcher matchUrl(String str) {
			if (str == null) {
				return null;
			}
			Pattern pattern = Pattern.compile(URLPATTERN);
			Matcher matcher = pattern.matcher(str);
			return (matcher.find()) ? matcher : null;
		}
		
		/**
		 * Map<?,?>の型から、Map<String,String>の型に変換する
		 * KeyがStringではない場合は無視する
		 * KeyがStringの場合で、
		 *　ValueがStringの場合、そのままput, String[]の場合、一番目のみput, それ以外はtoString()の値をput
		 *
		 * @param map Map<?,?>型のMap
		 * @return <String,String>のMapに整理して返す
		 */
		public Map<String, String> arrangeMap(Map<?,?> map) {
			if (map == null) {
				return null;
			}
			
			Map<String, String> arrangemap = new HashMap<String, String>();
			Iterator<?> ite = map.keySet().iterator();
			while(ite.hasNext()) {
				Object key= ite.next();
				if (!(key instanceof String)) {
					continue;
				}
				Object value = map.get(key);
				
				if (value instanceof String) {
					arrangemap.put((String)key, (String)value);
				} else if (value instanceof String[]) {
					String[] strs = (String[])value;
					if (strs.length == 0) {
						continue;
					}
					arrangemap.put((String)key, (String)strs[0]);
				} else {
					arrangemap.put((String)key, value.toString());
				}
			}
			
			return arrangemap;
		}
		
		/**
		 * URL文字列をURLデコードする
		 * @param str デコード前の文字列
		 * @return デコードされた文字列
		 */
		public String decodeUrl(String str) {
			if (str == null) {
				return null;
			}
			String decoded = null;
			try {
				decoded = URLDecoder.decode(str , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				decoded = str;
			}
			return decoded;
		}
	}

}
