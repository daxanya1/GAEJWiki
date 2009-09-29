package com.appspot.gaejwiki.common.wiki.block;

import java.util.HashMap;

import com.appspot.gaejwiki.common.wiki.block.base.SameAddBlockBase;

/**
 * WikiObject
 * 段落
 * @author daxanya
 * 
 * --
 * 
他のブロック要素を明示しない限り、段落となります。

行頭で ~ を指定した場合も段落になります。行頭書式の文字(~、-、+、:、>、|、#、//)を通常の文字として段落の先頭に書きたい場合は、行頭に~を記述して書くことができます。

段落の先頭は1文字分字下げされます。但し、番号なしリスト構造、番号付きリスト構造、引用文内の段落では字下げされません。定義リスト内の段落の先頭は1文字分字下げされます。
段落は、新たなブロック要素が現われるまで継続します。
段落は、他のブロック要素の子要素になることができます。
段落は、他のブロック要素を子要素にすることはできません。
 *
 * --
 */
public class ParagraphBlock extends SameAddBlockBase {

	// 行頭書式の文字(-、+、:、>、|、#、//)
	private static final char[] NOTPARAGRAPHCHAR = { QUOTATION, UNQUOTATION, FORMATED, UNNUMBEREDLIST, NUMBEREDLIST, DEFINEDLIST, HASH, HEADLINE, TABLE, CSV, COMMENTFIRST };
	private static final HashMap<Character, Boolean> NOTPARAGRAPHS = new HashMap<Character, Boolean>();
	
	static {
		for (char notpara : NOTPARAGRAPHCHAR) {
			NOTPARAGRAPHS.put(new Character(notpara), Boolean.TRUE);
		}
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {
		
		/**
		 * パラグラフのblockかどうかチェックする
		 * ~が先頭であれば、パラグラフ
		 * 他のブロック要素であればパラグラフではない
		 * それ以外はパラグラフとする
		 * @param line　一行分の文字列
		 * @return パラグラフであればtrue
		 */
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			// PARAGRAPH要素が一文字目であれば、パラグラフとする
			if (line.charAt(0) == PARAGRAPH) {
				return true;
			}
			
			// 他のブロック要素であればパラグラフではない
			if (NOTPARAGRAPHS.containsKey(new Character(line.charAt(0)))) {
				if (line.charAt(0) != COMMENTFIRST && line.charAt(0) != DEFINEDLIST) {
					return false;
				} else {
					// コメントと定義リストだけ特別扱い
					if (line.charAt(0) == COMMENTFIRST) {
						// コメントかどうか確認
						if (new CommentBlock.Checker().isThis(line)) {
							return false;
						}
					}
					if (line.charAt(0) == DEFINEDLIST) {
						// 定義リストの場合 どこかに|があれば定義リスト、それ以外は段落扱い
						if (new DefinedListBlock.Checker().isThis(line)) {
							return false;
						}
					}
				}
			}
		
			// それ以外はパラグラフとする。
			return true;
		}
	}

}
