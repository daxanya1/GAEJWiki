package com.appspot.gaejwiki.common.wiki;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;


public class WikiObjectBlockFactoryTest {

	public List<String> prepareBlockCheckLineList() {
		List<String> list = new ArrayList<String>();
		
		// 0 - 4
		list.add("���ʂ̒i��");
		list.add("~#�L���t���i��");
		list.add("~//�L���t���i��");
		list.add(">���p1");
		list.add(">>���p2");
		// 5 - 9
		list.add(">>>���p3");
		list.add("<�t���p1");
		list.add("<<�t���p2");
		list.add("<<<�t���p3");
		list.add(" ���`�ς�");
		// 10 - 14
		list.add(" //���`�ς�");
		list.add("*���o��");
		list.add("+�ԍ����X�g1");
		list.add("++�ԍ����X�g2");
		list.add("+++�ԍ����X�g3");
		// 15 - 19
		list.add("-���X�g1");
		list.add("--���X�g2");
		list.add("---���X�g3");
		list.add("//�R�����g");
		list.add("#�R���e���c");
		// 20 - 24
		list.add(":��`");
		list.add("|Table");
		list.add(",CSV");
		list.add("\\�֌W�Ȃ��i�i��)");
		list.add("�@�S�p�X�y�[�X�i�i��)");
		// 25 - 28
		list.add("�`�S�p�`���_�i�i��)");
		list.add("���S�p�i���o�[�i�i��)");
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
