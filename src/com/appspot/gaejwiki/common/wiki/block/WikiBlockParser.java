package com.appspot.gaejwiki.common.wiki.block;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class WikiBlockParser {

	private static final Logger logger = Logger.getLogger(WikiBlockParser.class.getName());

	
	/**
	 * �����񃊃X�g����AWikiObjectI�̃��X�g������ĕԂ�
	 * @param linelist
	 * @return
	 */
	public List<WikiObjectBlockI> parseBlock(WikiObjectBlockFactory factory, List<String> linelist) {
		List<WikiObjectBlockI> wikilist = new ArrayList<WikiObjectBlockI>();
		WikiObjectBlockI nowobject = null;

		if (linelist == null || linelist.size() == 0) {
			logger.fine("wikiblockparser linelist is null");
			return wikilist;
		}
		
		// ��s���擾����B
		for (String line : linelist) {
			if (line == null || line.length() == 0) {
				// ��؂�Ƃ݂Ȃ��B
				nowobject = null;
				continue;
			}
			
			WikiObjectBlockI wikiobject = factory.createWikiObjectBlock(line);
			if (wikiobject == null) {
				// ��؂�Ƃ݂Ȃ��B�i�����ɂ͂��Ȃ��͂��j
				assert(false);
				nowobject = null;
				continue;
			}
			
			// �R�����g���ǂ������f����
			if (wikiobject instanceof CommentBlock) {
				// �Ȃɂ������Ɏ��ցB
				continue;
			}
			
			// nowobject��null�łȂ��ꍇ�̓���d�l
			if (new Sub().isAddLine(nowobject, wikiobject)) {
				// ���̃u���b�N�ɍs�ǉ����Ď��ցB
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
					WikiObjectBlockI parenttoaddchild = new Sub().getParentToAddChild(nowobject);
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
	
	static public class Sub {
		/**
		 * nowobject��isAddline()=true�̏ꍇ�ɁA���̂ǂ��炩�ł����true
		 * �P�Dnowobject��isSameBlockAddLine()=true�ŁAnowobject��wikiobject�������N���X
		 * �Q�Dnowobject��isSameBlockAddLine()=false�ŁAwikiobject���i��
		 *
		 * @param nowobject
		 * @param wikiobject
		 * @return �����ɍ��v�����ꍇtrue
		 */
		public boolean isAddLine(WikiObjectBlockI nowobject, WikiObjectBlockI wikiobject) {
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
		public WikiObjectBlockI getParentToAddChild(WikiObjectBlockI nowobject) {
			if (nowobject == null) {
				return null;
			}
			
			if (nowobject.isAddChildBlock()) {
				// nowobject��isAddChildBlock�������炻�̂܂ܖ߂��B(���Ȃ��͂�)
				assert(false);
				return nowobject;
			}
			
			WikiObjectBlockI parentobject = nowobject.getParent();
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
