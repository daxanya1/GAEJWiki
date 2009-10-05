package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

/**
 * WikiObject
 * �i��
 * @author daxanya
 * 
 * --
 * 
���̃u���b�N�v�f�𖾎����Ȃ�����A�i���ƂȂ�܂��B

�s���� ~ ���w�肵���ꍇ���i���ɂȂ�܂��B�s�������̕���(~�A-�A+�A:�A>�A|�A#�A//)��ʏ�̕����Ƃ��Ēi���̐擪�ɏ��������ꍇ�́A�s����~���L�q���ď������Ƃ��ł��܂��B

�i���̐擪��1����������������܂��B�A���A�ԍ��Ȃ����X�g�\���A�ԍ��t�����X�g�\���A���p�����̒i���ł͎���������܂���B��`���X�g���̒i���̐擪��1����������������܂��B
�i���́A�V���ȃu���b�N�v�f��������܂Ōp�����܂��B
�i���́A���̃u���b�N�v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B
�i���́A���̃u���b�N�v�f���q�v�f�ɂ��邱�Ƃ͂ł��܂���B
 *
 * --
 */
public class ParagraphBlock extends SameAddBlockBase {

	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<p>");
		for (WikiObjectInlineI inline : inlinelist) {
			sb.append(inline.toHtmlString());
		}
		sb.append("</p>");
		sb.append(new Util().getLineSeparator());
		return sb.toString();
	}
	
	@Override
	public void paserInline(WikiInlineParser parser) {
		
		List<String> datalist = getRawlist();
		if (datalist == null || datalist.size() == 0) {
			return;
		}

		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (String line : datalist) {
			if (!first) { sb.append(new Util().getLineSeparator()); } else { first = false; }
			sb.append(line);
		}
		
		inlinelist.addAll(parser.parseInline(sb.toString()));
	}

	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		@Override
		public boolean isThis(String line) {
			// ���肹����true��Ԃ�
			return true;
		}
	}

}
