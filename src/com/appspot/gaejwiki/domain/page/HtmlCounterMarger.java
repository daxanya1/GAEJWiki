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

import java.util.Map;

/**
 *
 * @author Ryuichi Danno
 */
public class HtmlCounterMarger {

	public static final String TODAY = "today";
	public static final String YESTERDAY = "yesterday";
	public static final String TOTAL = "total";
	
	/**
	 * @param htmldata
	 * @param countermap
	 * @return
	 */
	public String margeHtml(String htmldata, Map<String, String> countermap) {
		return htmldata;
	}

}
