package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.NoChildParentBlockBase;



/**
 * WikiObject
 * 水平線
 * @author daxanya
 *
 * --
 * 
行頭で4つ以上の - を書くと水平線になります。

水平線は、他のブロック要素の子要素になることはできません。水平線が現われると他のブロック要素は終了します。
水平線は、他のブロック要素を子要素にすることはできません。
 * 
 * --

 */
public class HorizonBlock extends NoChildParentBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * 水平線かどうかチェックする
		 * 水平線要素があれば、水平線とする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 水平線であればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() < 4) {
				return false;
			}
			
			// ４文字目まで調べて、----だったらtrue
			String checkstr = line.substring(0, 4);
			return (HORIZON.equals(checkstr)) ? true : false;
		}
	}
}
