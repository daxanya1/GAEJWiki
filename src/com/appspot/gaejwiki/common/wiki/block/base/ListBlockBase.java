package com.appspot.gaejwiki.common.wiki.block.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;



/**
 * WikiObject
 * ���X�g�n
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public abstract class ListBlockBase implements WikiObjectBlockI {

	private WikiObjectBlockI parent = null;
	private List<String> rawlist = new ArrayList<String>();
	private List<WikiObjectInlineI> inlinelist = new ArrayList<WikiObjectInlineI>();
	private int level;
	
	private List<WikiObjectBlockI> childlist = new ArrayList<WikiObjectBlockI>();
	
	@Override
	public void addLine(String str) {
		rawlist.add(str);
	}
	
	@Override
	public void addChildBlock(WikiObjectBlockI wikiobject) {
		childlist.add(wikiobject);
		wikiobject.setParent(this);
	}
	
	@Override
	public boolean isAddChildBlock() {
		return true;
	}
	
	@Override
	public boolean isAddLine() {
		return true;
	}
	
	@Override
	public boolean isSameBlockAddLine() {
		return false;
	}
	
	@Override
	public boolean isReset() {
		return false;
	}
	
	@Override
	public boolean isAddToParent() {
		return true;
	}
	
	@Override
	public String toString() {
		return null;
	}

	@Override
	public WikiObjectBlockI getParent() {
		return parent;
	}
	

	@Override
	public void setParent(WikiObjectBlockI wikiobject) {
		parent = wikiobject;
	}
	
	protected List<String> getRawlist() {
		return rawlist;
	}	
	
	protected List<WikiObjectBlockI> getChildlist() {
		return childlist;
	}

	@Override
	public String toDebugString() {
		StringBuffer sb = new StringBuffer();
		for (String str : rawlist) {
			sb.append(str);
			sb.append("\n");
		}
		for (WikiObjectBlockI wikiobj : childlist) {
			sb.append("/c:");
			sb.append(wikiobj.toDebugString());
			sb.append(":c/");
		}
		return sb.toString();
	}

	@Override
	public void paserInline(WikiInlineParser parser) {
		if (parser == null) {
			return;
		}
		
		inlinelist.addAll(parser.parseInline(cutBlockChar(rawlist)));
		
		for (WikiObjectBlockI wikiobj : childlist) {
			wikiobj.paserInline(parser);
		}
	}
	
	@Override
	public int getLevel() {
		return level;
	}
	
	@Override
	public String toHtmlString() {
		StringBuffer sb = new StringBuffer();
		sb.append(toHtmlStringHeader());
		sb.append(new Util().toHtmlString(inlinelist, childlist));
		sb.append(toHtmlStringFooter());
		return sb.toString();
	}
	
	protected void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * �u���b�N�̐擪������؂�
	 * @param datalist
	 * @return �擪������؂���������̔z��
	 */
	abstract protected String cutBlockChar(List<String> datalist);
	
	/**
	 * HtmlString�̃w�b�_��Ԃ�
	 * @return
	 */
	abstract protected String toHtmlStringHeader();
	
	/**
	 * HtmlString�̃t�b�^��Ԃ�
	 * @return
	 */
	abstract protected String toHtmlStringFooter();


	public static class Util extends WikiObjectBlockI.Util {
		
		/**
		 * datalist����u���b�N��������菜���āA���x����ݒ肵�āA������ɂ��ĕԂ�
		 * @param block ListBlockBase��Object
		 * @param datalist �����񃊃X�g
		 * @param blockchar Block�̃J�b�g����ׂ�����
		 * @return datalist���琶������镶����
		 */
		public String cutBlockCharListBlockBase(ListBlockBase block, List<String> datalist, char blockchar) {
			if (datalist == null || datalist.size() == 0) {
				return null;
			}

			// list�̍ŏ������u���b�N�ŁA�ȉ��̍s�͒ʏ�s�Ȃ̂ō��Ȃ�
			StringBuffer sb = new StringBuffer();
			String datalistfirststr = datalist.get(0);
			block.setLevel(checkLevel(datalistfirststr, blockchar));
			sb.append(cutFrontChar(datalistfirststr, block.getLevel()));
			
			for (int i = 1; i < datalist.size(); i++) {
				sb.append(datalist.get(i));
			}
			
			return sb.toString();
		}
	}
}
