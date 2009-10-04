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

	@Override
	protected String cutData(String data) {
		return new Sub().cutData(data);
	}
	
	static public class Sub {
		public String cutData(String data) {
			if (data == null) {
				return null;
			}
			
			return (data.charAt(0) == LEFT) ? data.substring(LEFTFORMAT.length(), data.length()) : 
					(data.charAt(0) == RIGHT) ? data.substring(RIGHTFORMAT.length(), data.length()) :
					(data.charAt(0) == CENTER) ? data.substring(CENTERFORMAT.length(), data.length()) : null;
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {

		@Override
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
