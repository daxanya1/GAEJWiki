package com.appspot.gaejwiki.common.wiki;


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
public class SharpBlock implements WikiObjectI {

	private WikiObjectI parent = null;
	private String data = new String("");
	
	@Override
	public void addLine(String str) {
		data = str;
	}
	
	@Override
	public boolean isAddChildBlock() {
		return false;
	}

	@Override
	public boolean isAddLine() {
		return false;
	}
	
	@Override
	public boolean isAddToParent() {
		return true;
	}
	
	
	@Override
	public String toString() {
		return null;
	}
	
	@Override
	public void addChildBlock(WikiObjectI wikiobject) {
		// �Ȃɂ����Ȃ��B
	}

	@Override
	public WikiObjectI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		parent = wikiobject;
	}

	protected String getData() {
		return data;
	}

}
