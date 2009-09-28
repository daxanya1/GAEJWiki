package com.appspot.gaejwiki.common.wiki;


/**
 * WikiObject
 * #～のブロック
 * @author daxanya
 * 
 * --
 * 
#contents : 目次
#hr : 水平線
#br : 行間開け
#ref : 添付ファイル・画像の貼り付け
#clear : テキストの回り込みの解除
#comment,#pcomment, #article, #vote : フォーム

 *
 * --
 */
public class SharpBlock implements WikiObjectI {

	private WikiObjectI parent = null;
	private String data = new String("");
	
	@Override
	public void addLine(String str) {
		data = str;
	}
	
	@Override
	public boolean isAddChildBlock() {
		return false;
	}

	@Override
	public boolean isAddLine() {
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
	public void addChildBlock(WikiObjectI wikiobject) {
		// なにもしない。
	}

	@Override
	public WikiObjectI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectI wikiobject) {
		parent = wikiobject;
	}

	protected String getData() {
		return data;
	}

}
