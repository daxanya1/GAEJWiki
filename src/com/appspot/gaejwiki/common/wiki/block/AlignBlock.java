package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;

/**
 * WikiObject
 * 左寄せ・センタリング・右寄せ
 * @author daxanya
 * 
 * --
 * 
行頭で LEFT:、 CENTER:、 RIGHT: を記述すると、インライン要素が左寄せ、センタリング、右寄せされます。

LEFT:、CENTER:、RIGHT:は、他のブロック要素の子要素になることができます。
LEFT:、CENTER:、RIGHT:は、他のブロック要素を子要素にすることができません。

 *
 * --
 */
public class AlignBlock extends YesChildNoAddlineBlockBase {

	static public class Checker implements WikiObjectBlockI.Checker {

		/**
		 * 配置ブロックかどうかチェックする
		 * 配置要素が一文字目であれば、配置ブロックとする
		 * それ以外は違う
		 * @param line 一行分の文字列
		 * @return 配置ブロックであればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// LEFT:,CENTER:,RIGHT:のどれかだったらtrue
			return (equalsSubstring(line, CENTERFORMAT)) ? true : 
				(equalsSubstring(line, LEFTFORMAT)) ? true :
				(equalsSubstring(line, RIGHTFORMAT)) ? true : false;
		}
		
		private boolean equalsSubstring(String line, String key) {
			if (line.length() < key.length()) {
				return false;
			}
			return key.equals(line.substring(0, key.length()));
		}
	}
}
