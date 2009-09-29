package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;


/**
 * WikiObject
 * #～のブロック
 * @author daxanya
 * 
 * --
 * 
#contents : 目次
#hr : 水平線
#br : 行間開け
#ref : 添付ファイル・画像の貼り付け
#clear : テキストの回り込みの解除
#comment,#pcomment, #article, #vote : フォーム

 *
 * --
 */
public class HashBlock extends YesChildNoAddlineBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * #系かどうかチェックする
		 * #系要素が一文字目であれば、#系とする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return #系であればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// SHARP要素が一文字目であれば、#系とする
			return (line.charAt(0) == HASH) ? true : false;
		}
	}
}
