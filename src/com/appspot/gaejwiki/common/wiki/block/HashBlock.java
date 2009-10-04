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

	@Override
	protected String cutData(String data) {
		return new Sub().cutData(data);
	}
	
	static public class Sub {
		public String cutData(String data) {
			if (data == null) {
				return null;
			}
			
			return data.substring(1, data.length());
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// SHARP要素が一文字目であれば、#系とする
			return (line.charAt(0) == HASH) ? true : false;
		}
	}
}
