package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.appspot.gaejwiki.common.wiki.block.CommentBlock;
import com.appspot.gaejwiki.common.wiki.block.ParagraphBlock;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockFactory;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;

public class WikiParser {

	private static final Logger logger = Logger.getLogger(WikiParser.class.getName());

	
	public String parse(String str) {
		return null;
	}
	
	
	public static class BlockSub {
		
		/**
		 * 文字列リストから、WikiObjectIのリストを作って返す
		 * @param linelist
		 * @return
		 */
		public List<WikiObjectBlockI> parseBlock(WikiObjectBlockFactory factory, List<String> linelist) {
			List<WikiObjectBlockI> wikilist = new ArrayList<WikiObjectBlockI>();
			WikiObjectBlockI nowobject = null;

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
				if (isAddLine(nowobject, wikiobject)) {
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
						WikiObjectBlockI parenttoaddchild = getParentToAddChild(nowobject);
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
