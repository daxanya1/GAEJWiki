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

	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * #�n���ǂ����`�F�b�N����
		 * #�n�v�f���ꕶ���ڂł���΁A#�n�Ƃ���
		 * ����ȊO�͈Ⴄ
		 * @param line ��s���̕�����
		 * @return #�n�ł����true
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// SHARP�v�f���ꕶ���ڂł���΁A#�n�Ƃ���
			return (line.charAt(0) == HASH) ? true : false;
		}
	}
}
