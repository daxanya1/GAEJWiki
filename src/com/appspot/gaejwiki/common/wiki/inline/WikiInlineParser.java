package com.appspot.gaejwiki.common.wiki.inline;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory.WikiObjectInlineIPair;


public class WikiInlineParser {

	private static final Logger logger = Logger.getLogger(WikiInlineParser.class.getName());

	
	/**
	 * �����񃊃X�g����AWikiObjectI�̃��X�g������ĕԂ�
	 * @param linelist
	 * @return
	 */
	public List<WikiObjectInlineI> parseInline(WikiObjectInlineFactory factory, String line) {
		List<WikiObjectInlineI> wikilist = new ArrayList<WikiObjectInlineI>();

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
				inline.set(inlinestr, factory);
				wikilist.add(inline);
			}
			
			// ���ǉ�����������activeline����͂����āA�J��Ԃ�
			activeline = activeline.substring(length, activeline.length()-length);
		}
		return wikilist;
	}
	
}
