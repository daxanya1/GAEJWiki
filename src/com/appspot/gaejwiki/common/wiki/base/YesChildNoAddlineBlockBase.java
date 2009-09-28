package com.appspot.gaejwiki.common.wiki.base;


/**
 * WikiObject
 * q‹Ÿ‚É‚Í‚È‚ê‚é‚ªs’Ç‰Á‚ª‚Å‚«‚È‚¢
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public class YesChildNoAddlineBlockBase implements WikiObjectI {

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
	public boolean isSameBlockAddLine() {
		return false;
	}
	
	@Override
	public boolean isAddToParent() {
		return true;
	}
	
	@Override
	public boolean isReset() {
		return false;
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	@Override
	public void addChildBlock(WikiObjectI wikiobject) {
		// ‚È‚É‚à‚µ‚È‚¢B
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
