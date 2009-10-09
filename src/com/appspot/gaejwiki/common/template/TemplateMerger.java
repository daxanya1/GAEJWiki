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

package com.appspot.gaejwiki.common.template;

import java.util.Iterator;
import java.util.Map;

import com.appspot.gaejwiki.common.text.FileUtils;
import com.appspot.gaejwiki.common.xml.PtParam;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

/**
 * �e���v���[�g�f�[�^�ƃe���v���[�g�Ƀ}�[�W����p�����[�^���A������𐶐�����
 * @author Ryuichi Danno
 */
public class TemplateMerger {

	/**
	 * �e���v���[�g�f�[�^���̃p�����[�^��Map�̃p�����[�^�ƒu�������ďo�͂���
	 * @param loadtemplate �e���v���[�g�f�[�^
	 * @param replacemap �e���v���[�g�̃p�����[�^��u�������邽�߂�Map
	 * @return ����������
	 */
	public String makeHtml(TemplateData loadtemplate, TemplateReplaceMap replacemap) {
		if (loadtemplate == null || replacemap == null) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		DomainParameter domainparam = DomainParameter.getDomainParameter();
		Sub sub = new Sub();
		for (PtParam ptparam : loadtemplate) {
			sb.append(
					sub.margeMap(
							loadTemplateFile(domainparam.getTemplateFilePath(ptparam.getName())), 
							ptparam.getMap(), 
							replacemap));
		}
		
		return sb.toString();
	}
	
	protected String loadTemplateFile(String filepath) {
		return new FileUtils().getFile(filepath);
	}
	
	public static class Sub {

		/**
		 * �����񒆂�KeyMap��Key�Ɠ��������񂪂���΁Areplacemap��Key�ƕR�t����replacemap��Value�ƒu��������B
		 * @param filebody
		 * @param keymap
		 * @param replacemap
		 * @return �u��������̕�����
		 */
		public String margeMap(String filebody, Map<String, String> keymap, TemplateReplaceMap replacemap) {
			if (filebody == null) {
				return null;
			}
			if (keymap == null || replacemap == null) {
				return filebody;
			}
			
			String margebody = filebody;
			Iterator<String> ite = keymap.keySet().iterator();
			while(ite.hasNext()) {
				String key = ite.next();
				margebody = margebody.replaceAll(key, replacemap.get(key));
			}
			
			return margebody;
		}
	}

}
