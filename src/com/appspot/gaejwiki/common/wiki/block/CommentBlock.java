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

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;


/**
 * WikiObject
 * コメント
 * @author daxanya
 * 
 * --
 * 
行頭で // を指定すると、コメント行になります。コメント行は出力されない行です。

コメント行は、他の要素と無関係に行単位でどの位置にも記述できます。
コメント行は、前後の他の要素に何ら影響を及ぼしません。

※本来ブロックではないが、構造的にブロック扱いとする(パーサで処理する)
 *
 * --
 */
public class CommentBlock extends NoChildParentBlockBase {

	
	@Override
	public String toHtmlString() {
		// パース対象とならない
		assert(false);
		return null;
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		// なにもしない
	}
	
	@Override
	public int getLevel() {
		return -1;
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			// COMMENT.length()以下ならコメントではない
			if (line == null || line.length() < COMMENT.length()) {
				return false;
			}
			
			String checkstr = line.substring(0, COMMENT.length());
			return (COMMENT.equals(checkstr)) ? true : false;
		}
	}

}
