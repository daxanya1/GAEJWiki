package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;

/**
 * WikiObject
 * ���񂹁E�Z���^�����O�E�E��
 * @author daxanya
 * 
 * --
 * 
�s���� LEFT:�A CENTER:�A RIGHT: ���L�q����ƁA�C�����C���v�f�����񂹁A�Z���^�����O�A�E�񂹂���܂��B

LEFT:�ACENTER:�ARIGHT:�́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
LEFT:�ACENTER:�ARIGHT:�́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B

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
			
			// LEFT:,CENTER:,RIGHT:�̂ǂꂩ��������true
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
