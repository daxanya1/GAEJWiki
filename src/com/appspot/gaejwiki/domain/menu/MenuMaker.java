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
package com.appspot.gaejwiki.domain.menu;

import com.appspot.gaejwiki.domain.page.PageParam;

/**
 *
 * @author Ryuichi Danno
 */
public class MenuMaker {

	private static final String MENUPAGENAME = "menu";
	
	/**
	 * メニュー用ページパラメータを作成して返す
	 * @return メニュー用ページパラメータ
	 */
	public PageParam getMenuParam() {
		PageParam pageparam = new PageParam();
		pageparam.put(PageParam.PAGEKEY, MENUPAGENAME);
		return pageparam;
	}

}
