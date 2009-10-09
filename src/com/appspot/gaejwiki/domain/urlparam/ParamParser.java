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
	 * request����URL�ƃp�����[�^�����o�����͂���Map�ɂ߂ĕԂ�
	 * 
	 * URL�̃t�H�[�}�b�g�́Ahttp://�h���C��/path/aaaa?ref=bbbb&otherkey=cccc&...
	 * �Ƃ���
	 * 
	 * URL������o�����́A
	 * 1.�ǂ̃y�[�W�� PAGE=aaaa (required)
	 * 2.�ǂ�����Ă΂ꂽ�� REFER=bbbb
	 * 3.���̑��I�v�V����������� otherkey=cccc
	 * 
	 * 
	 * @param req HttpServletRequest
	 * @return �p�����[�^��Map
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
		 * URL����A�y�[�W����Ԃ��B
		 * URL��page���̑O��path�Ɛ��K�\������path���������v���Ă��Ȃ���Ζ���
		 * 
		 * @param str URL������
		 * @param requestcommand URL��page���̑O��path 
		 * @return page���̕������Ԃ��B�����ł����null
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
		 * URL�����񂪐��K�\���Ƀ}�b�`���Ă��邩���ׂ�
		 * @param str URL������
		 * @return �}�b�`���Ă����matcher��Ԃ��B�}�b�`���Ă��Ȃ����null
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
		 * Map<?,?>�̌^����AMap<String,String>�̌^�ɕϊ�����
		 * Key��String�ł͂Ȃ��ꍇ�͖�������
		 * Key��String�̏ꍇ�ŁA
		 *�@Value��String�̏ꍇ�A���̂܂�put, String[]�̏ꍇ�A��Ԗڂ̂�put, ����ȊO��toString()�̒l��put
		 *
		 * @param map Map<?,?>�^��Map
		 * @return <String,String>��Map�ɐ������ĕԂ�
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
		 * URL�������URL�f�R�[�h����
		 * @param str �f�R�[�h�O�̕�����
		 * @return �f�R�[�h���ꂽ������
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
