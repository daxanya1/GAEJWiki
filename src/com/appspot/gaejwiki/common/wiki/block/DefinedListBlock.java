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
package com.appspot.gaejwiki.common.wiki.block;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;


/**
 * WikiObject
 * 定義リスト
 * @author daxanya
 * 
 * --
 * 
行頭を : で始め、| 記号で区切ると、定義リストになります。定義リストは :、::、::: の3段階あります。定義リストの定義語、説明文は省略することができます。複数の連続した定義リストを記述し、2つ目以降の定義語を省略することで1つの定義語に対する複数の説明文を記述することができます。

行中に | がないと定義リストにはなりません。
定義語・説明文は、インライン要素のみ記述することができます。
定義リストは、他のブロック要素の子要素になることができます。他のリスト構造の子要素にする場合は、レベルを1段増やして記述します。引用文の子要素にする場合は、レベルを増やさずに記述します。
| の直後に ~ を記述すると段落を子要素にすることができます。
定義リストは、定義リストの次の行に他のブロック要素を記述することで、他のブロック要素を子要素にすることができます。
 *
 * --
 */
public class DefinedListBlock extends ListBlockBase {

	@Override
	protected String toHtmlStringFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String toHtmlStringHeader() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String cutBlockChar(List<String> datalist) {
		return new Util().cutBlockCharListBlockBase(this, datalist, DEFINEDLIST);
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// 定義リスト要素が一文字目でかつ、どこかに|があれば定義リストとする
			return (line.charAt(0) == DEFINEDLIST && line.indexOf(DEFINEDLIST_SECONDCHAR) >= 0) ? true : false;
		}
	}
}
