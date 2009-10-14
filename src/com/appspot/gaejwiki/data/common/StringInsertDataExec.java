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

package com.appspot.gaejwiki.data.common;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

/**
 * 文字列を後ろに挿入するためのDataExecI実装クラス。
 * すでに同一の文字列が合った場合は、そのまま返す
 * @author Ryuichi Danno
 */
public class StringInsertDataExec implements DataExecI {

	private String insertstr;
	
	public void setData(String insertstr) {
		this.insertstr = insertstr;
	}
	
	@Override
	public String exec(String data) {
		assert(insertstr != null);
		if (data == null) {
			return null;
		}
		boolean equalsflag = false;
		for (String str: new TextUtils().parseData(data)) {
			if (str.equals(insertstr)) {
				equalsflag = true;
				break;
			}
		}
		if (equalsflag) {
			// 合致していればそのまま戻る
			return data;
		} else {
			// 合致していなければ後ろに追加する
			StringBuffer sb = new StringBuffer(data);
			sb.append(insertstr);
			sb.append(DomainParameter.getDomainParameter().getLineSeparator());
			return sb.toString();
		}
	}

}
