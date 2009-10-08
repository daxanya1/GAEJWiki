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
	 * �����񃊃X�g����AWikiObjectI�̃��X�g������ĕԂ�
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
		
		// �A�N�e�B�u���C������ɂȂ�܂ő�����
		while (activeline.length() > 0) {
			WikiObjectInlineIPair wikiobjectpair = factory.createWikiObjectInline(activeline);
			if (wikiobjectpair == null) {
				// ��������B�i�����ɂ͂��Ȃ��͂��j
				assert(false);
				continue;
			}
			
			WikiObjectInlineI inline = wikiobjectpair.getInline();
			int length = wikiobjectpair.getLength();
			String inlinestr = activeline.substring(0, length);
			
			// Character�ł���΁A�A������\���������̂Œ��߂Ă���
			if (inline instanceof CharacterInline) {
				charactersb.append(inlinestr);
			} else {
				if (charactersb.length() > 0) {
					// Character�ɒ��܂��Ă������̂��ɓo�^����
					CharacterInline charainline = new CharacterInline();
					
					// characterinline�ɃZ�b�g����B�ċA�����͂��Ȃ�
					charainline.set(charactersb.toString(), null);
					wikilist.add(charainline);
					charactersb.delete(0, charactersb.length());
				}
				
				// ������inline�ɃZ�b�g����Ɠ����ɁA�ċA�I��inline�̒����p�[�X����
				inline.set(inlinestr, this);
				wikilist.add(inline);
			}
			
			// ���ǉ�����������activeline����͂����āA�J��Ԃ�
			activeline = activeline.substring(length, activeline.length());
		}
		
		// �����ōŌ��Character�ɒ��܂��Ă������̂��c���Ă�����o�^
		if (charactersb.length() > 0) {
			CharacterInline charainline = new CharacterInline();
			charainline.set(charactersb.toString(), null);
			wikilist.add(charainline);
		}

		return wikilist;
	}

	/**
	 * note���ɂ��ẮAparse����note��o�^���Ă����Anote�ԍ���Ԃ�
	 * html��������note���Ƃ��Ďg��
	 * @param noteInline
	 * @return note�ԍ�(1�͂��܂�)
	 */
	public int addNote(NoteInline note) {
		notelist.add(note);
		return notelist.size();
	}

	/**
	 * note����Ԃ�
	 * @return note����Html�t�H�[�}�b�g
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
	 * Headline(contents)���i�[����id��Ԃ�
	 * @param headlineblock
	 * @return contents�ԍ�(0�͂��܂�)
	 */
	public int addHeadline(HeadlineBlock headlineblock) {
		contentslist.add(headlineblock);
		// �Ԃ�ID��0�͂��܂�ɂ���B
		return contentslist.size() - 1;
	}

	/**
	 * �y�[�W�������݂��Ă����true��Ԃ�
	 * @param pagename
	 * @return �y�[�W�������݂��Ă����true
	 */
	public boolean checkPage(String pagename) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
