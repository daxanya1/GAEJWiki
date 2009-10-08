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
package com.appspot.gaejwiki.common.wiki.block;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.appspot.gaejwiki.common.wiki.block.CsvBlock;
import com.appspot.gaejwiki.common.wiki.block.DefinedListBlock;
import com.appspot.gaejwiki.common.wiki.block.FormatedBlock;
import com.appspot.gaejwiki.common.wiki.block.HashBlock;
import com.appspot.gaejwiki.common.wiki.block.HeadlineBlock;
import com.appspot.gaejwiki.common.wiki.block.HorizonBlock;
import com.appspot.gaejwiki.common.wiki.block.NumberedListBlock;
import com.appspot.gaejwiki.common.wiki.block.ParagraphBlock;
import com.appspot.gaejwiki.common.wiki.block.QuotationBlock;
import com.appspot.gaejwiki.common.wiki.block.TableBlock;
import com.appspot.gaejwiki.common.wiki.block.UnnumberedListBlock;


public class WikiObjectBlockCheckTest {

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
		list.add("#contents");
		// 20 - 24
		list.add(":��`");
		list.add("|Table");
		list.add(",CSV");
		list.add("\\�֌W�Ȃ��i�i��)");
		list.add("�@�S�p�X�y�[�X�i�i��)");
		// 25 - 29
		list.add("�`�S�p�`���_�i�i��)");
		list.add("���S�p�i���o�[�i�i��)");
		list.add(null);
		list.add("");
		list.add(":��`|");
		// 30 - 34
		list.add(":��`|test");
		list.add("----test");
		list.add("-----");
		list.add("CENTER:t");
		list.add("LEFT:tt");
		// 35 - 39
		list.add("LEFT::");
		list.add("RIGHT:tt");
		list.add("RiGHT:tt");
		list.add("CENTER");
		list.add("#contentst");
		// 40 - 44
		list.add("#br");
		list.add("#hr");
		list.add("#ref(1,2)");
		list.add("#vote(1,2,3)");
		list.add("#article");
		// 45 - 49
		list.add("#clear");
		list.add("#comment");
		list.add("#pcomment");
		list.add("#ref");
		list.add("#vote");
		
		return list;
	}

	
	@Test
	public void testBlockCheckIsComment() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new CommentBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 18) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	

	@Test
	public void testBlockCheckIsParagraph() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new ParagraphBlock.Checker().isThis(line));
		}
		
		// �p���O���t�͑S��true�Ƃ��Ă���B
		for (int i = 0; i<blist.size(); i++) {
			assertTrue(blist.get(i).booleanValue());
		}
		
	}
	
	@Test
	public void testBlockCheckIsFormated() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new FormatedBlock.Checker().isThis(line));
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
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new HeadlineBlock.Checker().isThis(line));
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
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new QuotationBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i >= 3 && i<= 8) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse(blist.get(i).booleanValue());
			}
			
		}
		
	}
	
	
	@Test
	public void testBlockCheckIsCsv() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new CsvBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 22) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	
	
	@Test
	public void testBlockCheckIsDefinedList() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new DefinedListBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 29 || i == 30) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	
	@Test
	public void testBlockCheckIsHorizon() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new HorizonBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 31 || i == 32) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	
	@Test
	public void testBlockCheckIsNumberedList() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new NumberedListBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i >= 12 && i <= 14) {
				assertTrue("" + i, blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	
	@Test
	public void testBlockCheckIsUnnumberedList() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new UnnumberedListBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i >= 15 && i <= 17) {
				assertTrue("" + i, blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	
	@Test
	public void testBlockCheckIsHash() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new HashBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 19 || (i >= 40 && i <= 47)) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	
	@Test
	public void testBlockCheckIsTable() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new TableBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i == 21) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	
	@Test
	public void testBlockCheckIsAlign() {
		List<String> list = prepareBlockCheckLineList();
		List<Boolean> blist = new ArrayList<Boolean>();
		for (String line : list) {
			blist.add(new AlignBlock.Checker().isThis(line));
		}
		
		for (int i = 0; i<blist.size(); i++) {
			if (i >= 33 && i <= 36) {
				assertTrue(blist.get(i).booleanValue());
			} else {
				assertFalse("" + i, blist.get(i).booleanValue());
			}
		}
	}
	

	
	
}
