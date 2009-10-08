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
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Ryuichi Danno
 */
public class WikiObjectInlineFactory {

	static final Map<Character, List<WikiObjectInlineI.Checker>> blockmap;
	
	static {
		blockmap = new HashMap<Character, List<WikiObjectInlineI.Checker>>();
		blockmap.put(getC(WikiObjectInlineI.AMPERSAND), getL2(new AmpersandChildInline.Checker(), new AmpersandChildParentInline.Checker()));
		blockmap.put(getC(WikiObjectInlineI.STRONG), getL2(new ItalicInline.Checker(), new StrongInline.Checker()));
		blockmap.put(getC(WikiObjectInlineI.ANGLEBRACKET), getL2(new PageInline.Checker(), new LinkInline.Checker()));
		blockmap.put(getC(WikiObjectInlineI.TILDE), getL(new NewlineInline.Checker()));
		blockmap.put(getC(WikiObjectInlineI.ROUNDBRACKET), getL(new NoteInline.Checker()));
		blockmap.put(getC(WikiObjectInlineI.PARCENT), getL(new StrikeInline.Checker()));
		// WikiNameは半角大文字全部が対象
		for (char c = 'A'; c <= 'Z'; c++) {
			blockmap.put(getC(c), getL(new WikiNameInline.Checker()));
		}
	}
	
	static public Character getC(char c) {
		return new Character(c);
	}
	
	static public List<WikiObjectInlineI.Checker> getL(WikiObjectInlineI.Checker checker) {
		List<WikiObjectInlineI.Checker> list = new ArrayList<WikiObjectInlineI.Checker>();
		list.add(checker);
		return list;
	}
	
	static public List<WikiObjectInlineI.Checker> getL2(WikiObjectInlineI.Checker checker1, WikiObjectInlineI.Checker checker2) {
		List<WikiObjectInlineI.Checker> list = new ArrayList<WikiObjectInlineI.Checker>();
		list.add(checker1);
		list.add(checker2);
		return list;
	}
	
	/**
	 * 文字列から判断してwikiobjectinlineを返す(中身はつめない)
	 * 
	 * @param line 文字列
	 * @return wikiobjectinline
	 */
	public WikiObjectInlineIPair createWikiObjectInline(String line) {
		if (line == null || line.length() == 0) {
			return null;
		}
		
		Character checkc = new Character(line.charAt(0));
		List<WikiObjectInlineI.Checker> listc = blockmap.get(checkc);
		
		if (listc != null) {
			for (WikiObjectInlineI.Checker checker : listc) {
				int length = checker.getMatchLength(line);
				if (length > 0) {
					return make(new Sub().createWikiObjectInlineFromChecker(checker), length);
				}
			}
		}
		
		// 何もない場合は文字Inlineを返す
		return make(new Sub().createWikiObjectInlineFromChecker(new CharacterInline.Checker()), new CharacterInline.Checker().getMatchLength(""));
	}
	
	public WikiObjectInlineIPair make(WikiObjectInlineI i, int len) {
		WikiObjectInlineIPair pair = new WikiObjectInlineIPair();
		pair.setInline(i);
		pair.setLength(len);
		return pair;
	}
	
	public class WikiObjectInlineIPair {
		
		private WikiObjectInlineI inline;
		private int length;
		public WikiObjectInlineI getInline() {
			return inline;
		}
		public void setInline(WikiObjectInlineI inline) {
			this.inline = inline;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}

	}
	
	static public class Sub {
	
		/**
		 * checkerのクラス名から、元クラス（ParagraphBlock.Checkerだったら、ParagraphBlock）名を取得し、インスタンスを作成して返す
		 * @param classname
		 * @return WikiObjectBlock
		 */
		protected WikiObjectInlineI createWikiObjectInlineFromChecker(WikiObjectInlineI.Checker checker) {
			try {
				String name = checker.getClass().getName();
				String classname = name.substring(0, name.length() - ".Checker".length());
				Class<?> entity = Class.forName(classname);
				return (WikiObjectInlineI)entity.newInstance();
			} catch (Exception e) {
				assert(false);
				return null;
			}
		}
	}
	
	
}
