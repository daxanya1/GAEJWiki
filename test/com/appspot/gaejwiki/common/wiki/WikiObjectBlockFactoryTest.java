package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;


public class WikiObjectBlockFactoryTest {

	public List<String> prepareBlockCheckLineList() {
		List<String> list = new ArrayList<String>();
		
		// 0 - 4
		list.add("普通の段落");
		list.add("~#記号付き段落");
		list.add("~//記号付き段落");
		list.add(">引用1");
		list.add(">>引用2");
		// 5 - 9
		list.add(">>>引用3");
		list.add("<逆引用1");
		list.add("<<逆引用2");
		list.add("<<<逆引用3");
		list.add(" 整形済み");
		// 10 - 14
		list.add(" //整形済み");
		list.add("*見出し");
		list.add("+番号リスト1");
		list.add("++番号リスト2");
		list.add("+++番号リスト3");
		// 15 - 19
		list.add("-リスト1");
		list.add("--リスト2");
		list.add("---リスト3");
		list.add("//コメント");
		list.add("#コンテンツ");
		// 20 - 24
		list.add(":定義");
		list.add("|Table");
		list.add(",CSV");
		list.add("\\関係ない（段落)");
		list.add("　全角スペース（段落)");
		// 25 - 28
		list.add("～全角チルダ（段落)");
		list.add("＃全角ナンバー（段落)");
		list.add(null);
		list.add("");
		
		return list;
	}
	
	@Test
	public void testBlockCheckIsParagraph() {
		WikiObjectBlockFactory.BlockCheck blockcheck = new WikiObjectBlockFactory.BlockCheck();
		
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(blockcheck.isParagraph(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 0 || i == 1 || i == 2 || (i >= 23 && i <= 26)) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse(blist.get(i).booleanValue());
			}
			
		}
		
	}
	
	@Test
	public void testBlockCheckIsFormated() {
		WikiObjectBlockFactory.BlockCheck blockcheck = new WikiObjectBlockFactory.BlockCheck();
		
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(blockcheck.isFormated(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 9 || i == 10) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse(blist.get(i).booleanValue());
			}
			
		}
		
	}
	
	@Test
	public void testBlockCheckIsHeadline() {
		WikiObjectBlockFactory.BlockCheck blockcheck = new WikiObjectBlockFactory.BlockCheck();
		
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(blockcheck.isHeadline(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 11) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse(blist.get(i).booleanValue());
			}
			
		}
		
	}
	
	@Test
	public void testBlockCheckIsQuotation() {
		WikiObjectBlockFactory.BlockCheck blockcheck = new WikiObjectBlockFactory.BlockCheck();
		
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(blockcheck.isQuotation(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i >= 3 && i<= 8) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse(blist.get(i).booleanValue());
			}
			
		}
		
	}
	
	
}
