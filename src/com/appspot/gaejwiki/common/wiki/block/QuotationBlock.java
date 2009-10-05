package com.appspot.gaejwiki.common.wiki.block;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.block.base.ListBlockBase;

/**
 * WikiObject
 * 引用
 * @author daxanya
 *
 * --
 *
行頭で > を指定すると、引用文になります。引用文は >、>>、>>> の3レベルあります。

引用文の中は、ブロック要素を明示しない限り、段落となります。
引用文は、空行が現われるまで継続します。
引用文内の段落は、新たな引用文またはブロック要素が現われるまで継続します。
引用文は、他のブロック要素の子要素になることができます。他の引用文の子要素にする場合は、レベルを1段増やして記述します。リスト構造の子要素にする場合はレベルを1段増やさずに記述します。
引用文は、他のブロック要素を子要素にすることができます。引用文の子要素となるリスト構造はレベルを1段増やさずに記述します。
リスト構造内の引用文から脱出する場合で、リスト構造を継続する場合は、<、<<、<<<を行頭に記述します。
 *
 * --
 */
public class QuotationBlock extends ListBlockBase{

	/* QUOTATION:true UNQUOTATION:false */
	private boolean quoteunquote = false;
	
	@Override
	protected String toHtmlStringFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String toHtmlStringHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String cutBlockChar(List<String> datalist) {
		if (datalist == null || datalist.size() == 0) {
			return null;
		}
		if (datalist.get(0).charAt(0) == QUOTATION) {
			quoteunquote = true;
			return new Util().cutBlockCharListBlockBase(this, datalist, QUOTATION);
		} else {
			quoteunquote = false;
			return new Util().cutBlockCharListBlockBase(this, datalist, UNQUOTATION);
		}
	}
	
	/**
	 * QUOTATIONかUNQUOTATIONかを返す
	 * @return QUOTATION:true UNQUOTATION:false
	 */
	public boolean isQuote() {
		return quoteunquote;
	}
	
	static public class Checker implements WikiObjectBlockI.Checker {

		@Override
		public boolean isThis(String line) {
			if (line == null || line.length() == 0) {
				return false;
			}
			
			return (line.charAt(0) == QUOTATION || line.charAt(0) == UNQUOTATION) ? true : false;
		}
	}
}
