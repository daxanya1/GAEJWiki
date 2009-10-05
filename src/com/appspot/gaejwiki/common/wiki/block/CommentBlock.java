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
