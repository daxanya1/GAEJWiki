package com.appspot.gaejwiki.common.wiki.inline;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory.WikiObjectInlineIPair;


public class WikiInlineParser {

	private static final Logger logger = Logger.getLogger(WikiInlineParser.class.getName());

	private WikiObjectInlineFactory factory = null;
	private WikiObjectBlockInfo info = null;
	
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
	
}
