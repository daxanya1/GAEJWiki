package com.appspot.gaejwiki.common.wiki;


/**
 * WikiObject
 * CSV
 * @author daxanya
 * 
 * --
 * 
�s���ŃJ���}(,)���L�q���A�C�����C���v�f���J���}��؂�ŋL�q����ƕ\�g�݂ɂȂ�܂��B

�C�����C���v�f�̓_�u���N�H�[�e�[�V����(")�ň͂ނ��Ƃ��ł��܂��B�_�u���N�H�[�e�[�V�����ň͂ނ��ƂŁA�J���}(,)���܂ރC�����C���v�f���L�q�ł��܂��B
�_�u���N�H�[�e�[�V����(")�ň͂񂾃f�[�^�̒��ŁA�_�u���N�H�[�e�[�V������2��("")�����邱�ƂŁA�_�u���N�H�[�e�[�V����(")���܂ރC�����C���v�f���L�q�ł��܂��B
�C�����C���v�f�̑���ɃC�R�[����2��(==)�L�q����ƁAcolspan���Ӗ����܂��B
�C�����C���v�f�̍���1�ȏ�̔��p�󔒕������L�q����ƉE�񂹂ɁA�C�����C���v�f�̍��E��1�ȏ�̔��p�󔒕������L�q����ƃZ���^�����O�ɂȂ�܂��B
�\�g�݂́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�\�g�݂́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂���B

 *
 * --
 */
public class CsvBlock implements WikiObjectI {

	private WikiObjectI parent = null;
	private String csv = new String("");
	
	@Override
	public void addLine(String str) {
		csv = str;
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
		return csv;
	}

}
