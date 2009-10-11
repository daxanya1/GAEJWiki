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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class WikiBlockParser {

	private static final Logger logger = Logger.getLogger(WikiBlockParser.class.getName());

	
	/**
	 * 文字列リストから、WikiObjectIのリストを作って返す
	 * @param linelist
	 * @return
	 */
	public List<WikiObjectBlockI> parseBlock(WikiObjectBlockFactory factory, List<String> linelist) {
		List<WikiObjectBlockI> wikilist = new ArrayList<WikiObjectBlockI>();
		WikiObjectBlockI nowobject = null;

		if (linelist == null || linelist.size() == 0) {
			logger.fine("wikiblockparser linelist is null");
			return wikilist;
		}
		
		// 一行ずつ取得する。
		for (String line : linelist) {
			if (line == null || line.length() == 0) {
				// 区切りとみなす。
				nowobject = null;
				continue;
			}
			
			WikiObjectBlockI wikiobject = factory.createWikiObjectBlock(line);
			if (wikiobject == null) {
				// 区切りとみなす。（ここにはこないはず）
				assert(false);
				nowobject = null;
				continue;
			}
			
			// コメントかどうか判断する
			if (wikiobject instanceof CommentBlock) {
				// なにもせずに次へ。
				continue;
			}
			
			// nowobjectがnullでない場合の特殊仕様
			if (new Sub().isAddLine(nowobject, wikiobject)) {
				// 今のブロックに行追加して次へ。
				nowobject.addLine(line);
				continue;
			}
			
			// wikiobjectにlineを追加する。
			wikiobject.addLine(line);
			
			// wikiobjectが子供になれないか、nowobjectがnullの場合、wikilistに追加
			if (!wikiobject.isAddToParent() || nowobject == null) {
				wikilist.add(wikiobject);
			} else {
				// blockを子供としてセットするが、nowobjectが子供をセットできない場合、親をたどる
				if (nowobject.isAddChildBlock()) {
					nowobject.addChildBlock(wikiobject);
				} else {
					WikiObjectBlockI parenttoaddchild = new Sub().getParentToAddChild(nowobject);
					if (parenttoaddchild == null) {
						// 親が全部子供セット不可な場合は、トップへ入れる
						wikilist.add(wikiobject);
					} else {
						// 親があったら、そちらの親にセットする
						parenttoaddchild.addChildBlock(wikiobject);
					}
				}
			}
			
			// リセットする場合はnowobjectをnullとする。
			if (wikiobject.isReset()) {
				nowobject = null;
			} else {
				nowobject = wikiobject;
			}
		}
		
		return wikilist;
	}
	
	static public class Sub {
		/**
		 * nowobjectがisAddline()=trueの場合に、次のどちらかであればtrue
		 * １．nowobjectがisSameBlockAddLine()=trueで、nowobjectとwikiobjectが同じクラス
		 * ２．nowobjectがisSameBlockAddLine()=falseで、wikiobjectが段落
		 *
		 * @param nowobject
		 * @param wikiobject
		 * @return 条件に合致した場合true
		 */
		public boolean isAddLine(WikiObjectBlockI nowobject, WikiObjectBlockI wikiobject) {
			if (nowobject == null || wikiobject == null) {
				return false;
			}
			
			if (nowobject.isAddLine()) {
				if (nowobject.isSameBlockAddLine()) {
					return (nowobject.getClass() == wikiobject.getClass()) ? true : false;
				} else {
					return (wikiobject instanceof ParagraphBlock) ? true : false;
				}
			} else {
				// ここにはこないはず。
				assert(false);
				return false;
			}
		}

		/**
		 * nowobjectからさか上って、isAddChildBlock()がtrueの親を探す。なければnull
		 * @param nowobject 現時点のWikiObjectI
		 * @return isAddChildBlock()がtrueの親のWikiObjectI
		 */
		public WikiObjectBlockI getParentToAddChild(WikiObjectBlockI nowobject) {
			if (nowobject == null) {
				return null;
			}
			
			if (nowobject.isAddChildBlock()) {
				// nowobjectがisAddChildBlockだったらそのまま戻す。(こないはず)
				assert(false);
				return nowobject;
			}
			
			WikiObjectBlockI parentobject = nowobject.getParent();
			while (parentobject != null) {
				if (parentobject.isAddChildBlock()) {
					return parentobject;
				}
				parentobject = parentobject.getParent();
			}
			
			return null;
		}
	}
}
