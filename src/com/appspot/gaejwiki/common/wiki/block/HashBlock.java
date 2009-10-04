package com.appspot.gaejwiki.common.wiki.block;

import com.appspot.gaejwiki.common.wiki.block.base.YesChildNoAddlineBlockBase;


/**
 * WikiObject
 * #�`�̃u���b�N
 * @author daxanya
 * 
 * --
 * 
#contents : �ڎ�
#hr : ������
#br : �s�ԊJ��
#ref : �Y�t�t�@�C���E�摜�̓\��t��
#clear : �e�L�X�g�̉�荞�݂̉���
#comment,#pcomment, #article, #vote : �t�H�[��

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
			
			// SHARP�v�f���ꕶ���ڂł���΁A#�n�Ƃ���
			return (line.charAt(0) == HASH) ? true : false;
		}
	}
}
