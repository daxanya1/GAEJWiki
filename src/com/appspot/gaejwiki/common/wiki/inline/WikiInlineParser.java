/**
 Copyright 2009 GAEJWiki Team.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.appspot.gaejwiki.common.wiki.inline;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.appspot.gaejwiki.common.wiki.block.HeadlineBlock;
import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI.Util;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory.WikiObjectInlineIPair;


public class WikiInlineParser {

	private static final Logger logger = Logger.getLogger(WikiInlineParser.class.getName());

	private WikiObjectInlineFactory factory = null;
	private WikiObjectBlockInfo info = null;
	private List<NoteInline> notelist = new ArrayList<NoteInline>();
	private List<HeadlineBlock> contentslist = new ArrayList<HeadlineBlock>();
	
	public void setWikiObjectInlineFactory(WikiObjectInlineFactory factory) {
		this.factory = factory;
	}
	
	public void setWikiObjectBlockInfo(WikiObjectBlockInfo info) {
		this.info = info;
	}
	
	/**
	 * 文字列リストから、WikiObjectIのリストを作って返す
	 * @param linelist
	 * @return
	 */
	public List<WikiObjectInlineI> parseInline(String line) {
		List<WikiObjectInlineI> wikilist = new ArrayList<WikiObjectInlineI>();
		if (factory == null || info == null) {
			logger.fine("wikiinlineparser factory or info is null");
			return wikilist;
		}

		if (line == null || line.length() == 0) {
			logger.fine("wikiinlineparser line is null");
			return wikilist;
		}
		
		String activeline = line;
		StringBuffer charactersb = new StringBuffer();
		
		// アクティブラインが空になるまで続ける
		while (activeline.length() > 0) {
			WikiObjectInlineIPair wikiobjectpair = factory.createWikiObjectInline(activeline);
			if (wikiobjectpair == null) {
				// 無視する。（ここにはこないはず）
				assert(false);
				continue;
			}
			
			WikiObjectInlineI inline = wikiobjectpair.getInline();
			int length = wikiobjectpair.getLength();
			String inlinestr = activeline.substring(0, length);
			
			// Characterであれば、連続する可能性が高いので貯めておく
			if (inline instanceof CharacterInline) {
				charactersb.append(inlinestr);
			} else {
				if (charactersb.length() > 0) {
					// Characterに貯まっていたものを先に登録する
					CharacterInline charainline = new CharacterInline();
					
					// characterinlineにセットする。再帰処理はしない
					charainline.set(charactersb.toString(), null);
					wikilist.add(charainline);
					charactersb.delete(0, charactersb.length());
				}
				
				// ここでinlineにセットすると同時に、再帰的にinlineの中をパースする
				inline.set(inlinestr, this);
				wikilist.add(inline);
			}
			
			// 今追加した部分をactivelineからはずして、繰り返す
			activeline = activeline.substring(length, activeline.length());
		}
		
		// ここで最後にCharacterに貯まっていたものが残っていたら登録
		if (charactersb.length() > 0) {
			CharacterInline charainline = new CharacterInline();
			charainline.set(charactersb.toString(), null);
			wikilist.add(charainline);
		}

		return wikilist;
	}

	/**
	 * note情報については、parse時にnoteを登録しておき、note番号を返す
	 * html生成時にnote情報として使う
	 * @param noteInline
	 * @return note番号(1はじまり)
	 */
	public int addNote(NoteInline note) {
		notelist.add(note);
		return notelist.size();
	}

	/**
	 * note情報を返す
	 * @return note情報のHtmlフォーマット
	 */
	public String toNoteHtmlString() {
		if (notelist == null || notelist.size() == 0) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("<div id=\"note\">");
		sb.append("<hr class=\"note_hr\" />");
		boolean first = true;
		for (NoteInline note : notelist) {
			if (!first) { sb.append(new Util().getLineSeparator()); } else { first = false; }
			sb.append(note.toNoteHtmlString());
		}
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * Headline(contents)を格納してidを返す
	 * @param headlineblock
	 * @return contents番号(0はじまり)
	 */
	public int addHeadline(HeadlineBlock headlineblock) {
		contentslist.add(headlineblock);
		// 返すIDは0はじまりにする。
		return contentslist.size() - 1;
	}

	/**
	 * ページ名が存在していればtrueを返す
	 * @param pagename
	 * @return ページ名が存在していればtrue
	 */
	public boolean checkPage(String pagename) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
