package com.appspot.gaejwiki.common.wiki.block.base;

import java.util.ArrayList;
import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;
import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;



/**
 * WikiObject
 * リスト系
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
	 * ブロックの先頭部分を切る
	 * @param datalist
	 * @return 先頭部分を切った文字列の配列
	 */
	abstract protected String cutBlockChar(List<String> datalist);
	
	/**
	 * HtmlStringのヘッダを返す
	 * @return
	 */
	abstract protected String toHtmlStringHeader();
	
	/**
	 * HtmlStringのフッタを返す
	 * @return
	 */
	abstract protected String toHtmlStringFooter();


	public static class Util extends WikiObjectBlockI.Util {
		
		/**
		 * datalistからブロック文字を取り除いて、レベルを設定して、文字列にして返す
		 * @param block ListBlockBaseのObject
		 * @param datalist 文字列リスト
		 * @param blockchar Blockのカットするべき文字
		 * @return datalistから生成される文字列
		 */
		public String cutBlockCharListBlockBase(ListBlockBase block, List<String> datalist, char blockchar) {
			if (datalist == null || datalist.size() == 0) {
				return null;
			}

			// listの最初だけブロックで、以下の行は通常行なので削らない
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
