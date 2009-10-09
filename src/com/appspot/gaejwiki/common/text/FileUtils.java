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

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * �t�@�C���Ɋւ��郆�[�e�B���e�B�N���X
 * @author Ryuichi Danno
 */
public class FileUtils {

	/**
	 * filepath�̃t�@�C����ǂݍ��ݕ�����ɂ��ĕԂ�
	 * @param filepath �t�@�C���p�X
	 * @return �ǂݍ��܂ꂽ�t�@�C���̕�����
	 */
	public String getFile(String filepath) {
		StringBuffer sb = new StringBuffer();
        FileReader in = null;
        BufferedReader br = null;
        try {
            in = new FileReader(filepath);
            br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        } finally {
        	try {
	            if (br != null) { br.close(); }
	            if (in != null) { in.close(); }
        	} catch (Exception e){
        		//
        	}
        }

	}
}
