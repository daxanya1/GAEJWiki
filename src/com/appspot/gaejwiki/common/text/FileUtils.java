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
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.appspot.gaejwiki.domain.setting.DomainParameter;

/**
 * ファイルに関するユーティリティクラス
 * @author Ryuichi Danno
 */
public class FileUtils {

	/**
	 * filepathのファイルを読み込み文字列にして返す
	 * @param filepath ファイルパス
	 * @param includebr 改行を残す場合はtrue
	 * @return 読み込まれたファイルの文字列
	 */
	public String getFile(String filepath, boolean includebr) {
		StringBuffer sb = new StringBuffer();
		FileInputStream is = null;
		InputStreamReader in = null;
        BufferedReader br = null;
        
        DomainParameter domainparam = DomainParameter.getDomainParameter();
        try {
            is  = new FileInputStream(filepath);
            in = new InputStreamReader(is, "UTF-8");
//            in = new FileReader(filepath);
            br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                if (includebr) {
                    sb.append(domainparam.getLineSeparator());
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        } finally {
        	try {
	            if (br != null) { br.close(); }
	            if (in != null) { in.close(); }
	            if (is != null) { is.close(); }
        	} catch (Exception e){
        		//
        	}
        }

	}
}
