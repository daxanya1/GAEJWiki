package com.appspot.gaejwiki.common.wiki.inline;

import java.util.List;

/**
 * WikiObject
 * inline ����
 * @author daxanya
 *
 *
 *�s���ŃC�����C���v�f�� (( �� )) �ł͂��ނƁA����*3���쐬����A�s���ɒ��߂ւ̃����N���\���܂��B

���߂́A���̃C�����C���v�f�̎q�v�f�ɂȂ邱�Ƃ��ł��܂��B�e�v�f�͒��ߕ��ł͂Ȃ��A���߂ւ̃����N�ɔ��f����܂��B
���߂́A���̃C�����C���v�f���q�v�f�ɂ��邱�Ƃ��ł��܂��B�q�v�f�͒��ߕ��ɔ��f����܂��B
 *
 */

public class NoteInline implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	private List<WikiObjectInlineI> childlist = null;
	private int noteno = -1;
	
	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}
	
	@Override
	public void set(String str, WikiInlineParser parser) {
		rawdata = str;
		// �ċA�������s��
		String line = new Util().matchSet(rawdata, getPattern());
		if (line != null) {
			childlist = parser.parseInline(line);
		}
		noteno = parser.addNote(this);
	}
	
	@Override
	public String toDebugString() {
		return new Util().toDebugString("note", childlist);
	}
		
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (childlist != null) {
			for (WikiObjectInlineI inline : childlist) {
				sb.append(inline.toString());
			}
		}
		return sb.toString();
	}

	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(toHtmlStringHeader());
		sb.append("*");
		sb.append(new Integer(noteno).toString());
		sb.append("</a>");
		return sb.toString();
	}

	public String toHtmlStringHeader() {
		String nostr = new Integer(noteno).toString();
		return "<a id=\"notetext_" + nostr + "\" href=\"#notefoot_" + nostr + "\" class=\"note_super\" title=\"" + toString() + "\">";
	}
	
	/**
	 * note����Ԃ�
	 * @return note����Html�t�H�[�}�b�g
	 */
	public String toNoteHtmlString() {
		if (noteno == -1) {
			return null;
		}
		String nostr = new Integer(noteno).toString();
		StringBuffer sb = new StringBuffer();
		sb.append("<a id=\"notefoot_" + nostr + "\" href=\"#notetext_" + nostr + "\" class=\"note_super\">");
		sb.append("*" + nostr);
		sb.append("</a>");
		sb.append(new Util().getLineSeparator());
		sb.append("<span class=\"small\">");
		for (WikiObjectInlineI inline : childlist) {
			sb.append(inline.toHtmlString());
		}
		sb.append("</span><br />");
		return sb.toString();
	}
	
	public String getPattern() {
		return NOTEFORMATPATTERN;
	}

	
	/**
	 * ���߂��ǂ����m�F
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, NOTEFORMATPATTERN);
		}
	}

}
