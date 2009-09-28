package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WikiParser {

	private static final Logger logger = Logger.getLogger(WikiParser.class.getName());

	
	/**
	 * 文字列リストから、WikiObjectIのリストを作って返す
	 * @param linelist
	 * @return
	 */
	public List<WikiObjectI> parse(WikiObjectBlockFactory factory, List<String> linelist) {
		List<WikiObjectI> wikilist = new ArrayList<WikiObjectI>();
		WikiObjectI nowobject = null;

		if (linelist == null || linelist.size() == 0) {
			logger.fine("wikiparser linelist is null");
			return wikilist;
		}
		
		// 一行ずつ取得する。
		for (String line : linelist) {
			if (line == null || line.length() == 0) {
				// 区切りとみなす。
				nowobject = null;
				continue;
			}
			
			WikiObjectI wikiobject = factory.createWikiObject(line);
			if (wikiobject == null) {
				// どれでもなかったらコメントかどうか判断する
				if (new WikiObjectBlockFactory.BlockCheck().isComment(line)) {
					// なにもせずに次へ。
					continue;
				}
				
				// それ以外は区切りとみなす。（ここにはこないはず）
				assert(false);
				nowobject = null;
				continue;
			}
			
			// nowobjectがnullでない場合の特殊仕様
			if (new Sub().isAddLine(nowobject, wikiobject)) {
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
					WikiObjectI parenttoaddchild = new Sub().getParentToAddChild(nowobject);
					if (parenttoaddchild == null) {
						// 親が全部子供セット不可な場合は、トップへ入れる
						wikilist.add(wikiobject);
					} else {
						// 親があったら、そちらの親にセットする
						parenttoaddchild.addChildBlock(wikiobject);
					}
				}
			}
			
			// nowobjectがnullの場合、行追加ができるかどうかで、nowobjectへセットするかどうかが決まる。
			if (wikiobject.isAddLine()) {
				nowobject = wikiobject;
			} else {
				nowobject = null;
			}
		}
		
		return wikilist;
	}
	
	public static class Sub {
		
		/**
		 * nowobjectがFormatedではなくて、wikiobjectが段落の場合、もしくは
		 * nowobjectがFormatedで、wikiobjectがFormatedの場合、true
		 *
		 * @param nowobject
		 * @param wikiobject
		 * @return 条件に合致した場合true
		 */
		public boolean isAddLine(WikiObjectI nowobject, WikiObjectI wikiobject) {
			if (nowobject == null || wikiobject == null) {
				return false;
			}
			return ((wikiobject instanceof ParagraphBlock && !(nowobject instanceof FormatedBlock))
					|| (wikiobject instanceof FormatedBlock && nowobject instanceof FormatedBlock));
		}

		/**
		 * nowobjectからさか上って、isAddChildBlock()がtrueの親を探す。なければnull
		 * @param nowobject 現時点のWikiObjectI
		 * @return isAddChildBlock()がtrueの親のWikiObjectI
		 */
		public WikiObjectI getParentToAddChild(WikiObjectI nowobject) {
			if (nowobject == null) {
				return null;
			}
			
			if (nowobject.isAddChildBlock()) {
				// nowobjectがisAddChildBlockだったらそのまま戻す。(こないはず)
				assert(false);
				return nowobject;
			}
			
			WikiObjectI parentobject = nowobject.getParent();
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
