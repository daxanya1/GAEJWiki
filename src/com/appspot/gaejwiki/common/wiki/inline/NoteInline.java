package com.appspot.gaejwiki.common.wiki.inline;

import java.util.List;

/**
 * WikiObject
 * inline 注釈
 * @author daxanya
 *
 *
 *行中でインライン要素を (( と )) ではさむと、注釈*3が作成され、行中に注釈へのリンクが貼られます。

注釈は、他のインライン要素の子要素になることができます。親要素は注釈文ではなく、注釈へのリンクに反映されます。
注釈は、他のインライン要素を子要素にすることができます。子要素は注釈文に反映されます。
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
		// 再帰処理を行う
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
	 * note情報を返す
	 * @return note情報のHtmlフォーマット
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
	 * 注釈かどうか確認
	 */
	static public class Checker implements WikiObjectInlineI.Checker {

		public int getMatchLength(String str) {
			return new Util().getRegexMatcherLength(str, NOTEFORMATPATTERN);
		}
	}

}
