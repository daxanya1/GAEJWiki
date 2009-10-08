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
package com.appspot.gaejwiki.domain.page;

import java.util.HashMap;
import java.util.Map;

import com.appspot.gaejwiki.data.dao.WikiData;
import com.appspot.gaejwiki.data.dao.WikiInfo;
import com.appspot.gaejwiki.domain.urlparam.ParamParser;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 *
 * @author Ryuichi Danno
 */
public class PageLoader {

	/**
	 * bodyparam��PAGE�L�[��Value����y�[�W�������o���āA�y�[�W���擾���A���I�Ȓl���}�b�s���O���ĕԂ�
	 * �y�[�W�̎擾���@�́A
	 * 1.�J�E���^���R�[�h����f�[�^�����o��
	 * 2.�J�E���^�̒l���C���N�������g���ĕۑ�����
	 * 3.Memcached���y�[�W����Key�ɂ��Ċm�F����B
	 * 4.3�Ŏ擾�ł��Ȃ������ꍇ�A�f�[�^���R�[�h����ŐV��data�����o���āAMemcached�Ɋi�[
	 * 5.���I�Ȓl�i��ɃJ�E���^�j���}�b�s���O���ĕԂ�
	 * 
	 * @param bodyparam URL�p�����[�^��Map
	 * @return HTML������
	 */
	public String loadPage(Map<String, String> bodyparam) {
		if (bodyparam == null) {
			return null;
		}
		String pagename = bodyparam.get(ParamParser.PAGEKEY);
		if (pagename == null) {
			return null;
		}
		
		Sub sub = new Sub();
		WikiInfo info = sub.getWikiInfo(pagename);
		String htmldata = sub.getHtmlData(info);
		Map<String, String> countermap = sub.createCounterMap(info);
		return new HtmlCounterMarger().margeHtml(htmldata, countermap);
	}
	
	static public class Sub {
		
		public String getHtmlData(WikiInfo info) {
			assert(info != null);
			WikiData.Util datautil = new WikiData.Util();
			Key datakey = datautil.makeKey(info.getKey(), info.getVersion());
			
			MemcacheService memcache = getMemcacheService();
			String memhtmldata = (String)memcache.get(datakey.toString());
			if (memhtmldata != null) {
				return memhtmldata;
			}
			
			WikiData data = datautil.loadData(datakey, false, true);
			if (data == null) {
				return null;
			}
			String htmldata = data.getHtmldata().toString();
			memcache.put(datakey.toString(), htmldata);
			return htmldata;
		}
		
		public MemcacheService getMemcacheService() {
			return MemcacheServiceFactory.getMemcacheService();
		}

		/**
		 * @param info
		 * @return
		 */
		public Map<String, String> createCounterMap(WikiInfo info) {
			assert(info != null);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put(HtmlCounterMarger.TODAY, info.getTodaycounter().toString());
			map.put(HtmlCounterMarger.YESTERDAY, info.getYesterdaycounter().toString());
			map.put(HtmlCounterMarger.TOTAL, info.getTotalcounter().toString());
			
			return map;
		}
		
		/**
		 * @param pagename
		 * @return
		 */
		public WikiInfo getWikiInfo(String pagename) {
			assert(pagename != null);
			
			WikiInfo.Util infoutil = new WikiInfo.Util();
			WikiInfo info = infoutil.loadAndIncrementData(infoutil.makeKey(pagename));
			
			// ���s�̏ꍇ�A�R��ăg���C
			if (info == null) {
				for (int i=0; i<3; i++) {
					// �X���[�v�������ق����悢�H
					info = infoutil.loadAndIncrementData(infoutil.makeKey(pagename));
					if (info != null) {
						break;
					}
				}
			}
			
			return info;
		}

	}

}
