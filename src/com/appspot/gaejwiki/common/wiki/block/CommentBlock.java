package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;


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

	/**
	 * コメント行かどうか確認
	 * @param line
	 * @return コメント行ならtrue
	 */
	static public class Checker implements WikiObjectBlockI.Checker {
		
		public boolean isThis(String line) {
			// ２文字以下ならコメントではない
			if (line == null || line.length() < 2) {
				return false;
			}
			
			// ２文字目まで調べて、//だったらtrue
			String checkstr = line.substring(0, 2);
			return (COMMENT.equals(checkstr)) ? true : false;
		}
	}
}
