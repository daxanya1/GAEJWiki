package com.appspot.gaejwiki.common.wiki.block;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;

/**
 * WikiObject
 * ���p
 * @author daxanya
 *
 * --
 *
�s���� > ���w�肷��ƁA���p���ɂȂ�܂��B���p���� >�A>>�A>>> ��3���x������܂��B

���p���̒��́A�u���b�N�v�f�𖾎����Ȃ�����A�i���ƂȂ�܂��B
���p���́A��s��������܂Ōp�����܂��B
���p�����̒i���́A�V���Ȉ��p���܂��̓u���b�N�v�f��������܂Ōp�����܂��B
���p���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B���̈��p���̎q�v�f�ɂ���ꍇ�́A���x����1�i���₵�ċL�q���܂��B���X�g�\���̎q�v�f�ɂ���ꍇ�̓��x����1�i���₳���ɋL�q���܂��B
���p���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B���p���̎q�v�f�ƂȂ郊�X�g�\���̓��x����1�i���₳���ɋL�q���܂��B
���X�g�\�����̈��p������E�o����ꍇ�ŁA���X�g�\�����p������ꍇ�́A<�A<<�A<<<���s���ɋL�q���܂��B
 *
 * --
 */
public class QuotationBlock extends ListBlockBase{

	/* QUOTATION:true UNQUOTATION:false */
	private boolean quoteunquote = false;
	
	@Override
	protected String toHtmlStringFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String toHtmlStringHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String cutBlockChar(List<String> datalist) {
		if (datalist == null || datalist.size() == 0) {
			return null;
		}
		if (datalist.get(0).charAt(0) == QUOTATION) {
			quoteunquote = true;
			return new Util().cutBlockCharListBlockBase(this, datalist, QUOTATION);
		} else {
			quoteunquote = false;
			return new Util().cutBlockCharListBlockBase(this, datalist, UNQUOTATION);
		}
	}
	
	/**
	 * QUOTATION��UNQUOTATION����Ԃ�
	 * @return QUOTATION:true UNQUOTATION:false
	 */
	public boolean isQuote() {
		return quoteunquote;
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {

		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (line.charAt(0) == QUOTATION || line.charAt(0) == UNQUOTATION) ? true : false;
		}
	}
}
