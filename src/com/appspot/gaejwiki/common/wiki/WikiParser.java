package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.appspot.gaejwiki.common.wiki.base.WikiObjectI;

public class WikiParser {

	private static final Logger logger = Logger.getLogger(WikiParser.class.getName());

	
	/**
	 * �����񃊃X�g����AWikiObjectI�̃��X�g������ĕԂ�
	 * @param linelist
	 * @return
	 */
	public List<WikiObjectI> parse(WikiObjectBlockFactory factory, List<String> linelist) {
		List<WikiObjectI> wikilist = new ArrayList<WikiObjectI>();
		WikiObjectI nowobject = null;

		if (linelist == null || linelist.size() == 0) {
			logger.fine("wikiparser linelist is null");
			return wikilist;
		}
		
		// ��s���擾����B
		for (String line : linelist) {
			if (line == null || line.length() == 0) {
				// ��؂�Ƃ݂Ȃ��B
				nowobject = null;
				continue;
			}
			
			WikiObjectI wikiobject = factory.createWikiObject(line);
			if (wikiobject == null) {
				// �ǂ�ł��Ȃ�������R�����g���ǂ������f����
				if (new WikiObjectBlockFactory.BlockCheck().isComment(line)) {
					// �Ȃɂ������Ɏ��ցB
					continue;
				}
				
				// ����ȊO�͋�؂�Ƃ݂Ȃ��B�i�����ɂ͂��Ȃ��͂��j
				assert(false);
				nowobject = null;
				continue;
			}
			
			// nowobject��null�łȂ��ꍇ�̓���d�l
			if (new Sub().isAddLine(nowobject, wikiobject)) {
				nowobject.addLine(line);
				continue;
			}
			
			// wikiobject��line��ǉ�����B
			wikiobject.addLine(line);
			
			// wikiobject���q���ɂȂ�Ȃ����Anowobject��null�̏ꍇ�Awikilist�ɒǉ�
			if (!wikiobject.isAddToParent() || nowobject == null) {
				wikilist.add(wikiobject);
			} else {
				// block���q���Ƃ��ăZ�b�g���邪�Anowobject���q�����Z�b�g�ł��Ȃ��ꍇ�A�e�����ǂ�
				if (nowobject.isAddChildBlock()) {
					nowobject.addChildBlock(wikiobject);
				} else {
					WikiObjectI parenttoaddchild = new Sub().getParentToAddChild(nowobject);
					if (parenttoaddchild == null) {
						// �e���S���q���Z�b�g�s�ȏꍇ�́A�g�b�v�֓����
						wikilist.add(wikiobject);
					} else {
						// �e����������A������̐e�ɃZ�b�g����
						parenttoaddchild.addChildBlock(wikiobject);
					}
				}
			}
			
			// ���Z�b�g����ꍇ��nowobject��null�Ƃ���B
			if (wikiobject.isReset()) {
				nowobject = null;
			} else {
				nowobject = wikiobject;
			}
		}
		
		return wikilist;
	}
	
	public static class Sub {
		
		/**
		 * nowobject��isAddline()=true�̏ꍇ�ɁA���̂ǂ��炩�ł����true
		 * �P�Dnowobject��isSameBlockAddLine()=true�ŁAnowobject��wikiobject�������N���X
		 * �Q�Dnowobject��isSameBlockAddLine()=false�ŁAwikiobject���i��
		 *
		 * @param nowobject
		 * @param wikiobject
		 * @return �����ɍ��v�����ꍇtrue
		 */
		public boolean isAddLine(WikiObjectI nowobject, WikiObjectI wikiobject) {
			if (nowobject == null || wikiobject == null) {
				return false;
			}
			
			if (nowobject.isAddLine()) {
				if (nowobject.isSameBlockAddLine()) {
					return (nowobject.getClass() == wikiobject.getClass()) ? true : false;
				} else {
					return (wikiobject instanceof ParagraphBlock) ? true : false;
				}
			} else {
				// �����ɂ͂��Ȃ��͂��B
				assert(false);
				return false;
			}
		}

		/**
		 * nowobject���炳������āAisAddChildBlock()��true�̐e��T���B�Ȃ����null
		 * @param nowobject �����_��WikiObjectI
		 * @return isAddChildBlock()��true�̐e��WikiObjectI
		 */
		public WikiObjectI getParentToAddChild(WikiObjectI nowobject) {
			if (nowobject == null) {
				return null;
			}
			
			if (nowobject.isAddChildBlock()) {
				// nowobject��isAddChildBlock�������炻�̂܂ܖ߂��B(���Ȃ��͂�)
				assert(false);
				return nowobject;
			}
			
			WikiObjectI parentobject = nowobject.getParent();
			while (parentobject != null) {
				if (parentobject.isAddChildBlock()) {
					return parentobject;
				}
				parentobject = parentobject.getParent();
			}
			
			return null;
		}
	}
}
