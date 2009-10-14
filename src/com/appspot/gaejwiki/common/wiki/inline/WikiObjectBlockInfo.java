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
package com.appspot.gaejwiki.common.wiki.inline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.common.wiki.block.HeadlineBlock;
import com.appspot.gaejwiki.domain.setting.DomainParameter;

/**
 * Block解析後、Inlineをパースする際に必要とする情報
 * #contents時の見出しや、note情報等
 *
 * @author Ryuichi Danno
 */
public class WikiObjectBlockInfo {

	private final Set<String> allpageset = new HashSet<String>();
	private final List<NoteInline> notelist = new ArrayList<NoteInline>();
	private final List<HeadlineBlock> contentslist = new ArrayList<HeadlineBlock>();
	private final Set<String> pageset = new HashSet<String>();
	private final Set<String> nonexistspageset = new HashSet<String>();
	private String accesspagename = null;
	
	/**
	 */
	public String getAccessPagename() {
		return accesspagename;
		
	}

	/**
	 * @param accesspagename
	 */
	public void setAccessPagename(String accesspagename) {
		this.accesspagename = accesspagename;
		
	}
	
	/**
	 * @param allPageData
	 */
	public void setAllPageData(String data) {
		if (data == null) {
			return;
		}
		
		for (String page: new TextUtils().parseData(data)) {
			allpageset.add(page);
		}
		
	}

	/**
	 * note情報を返す
	 * @return note情報のHtmlフォーマット
	 */
	public String toNoteHtmlString() {
		if (getNoteList().size() == 0) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("<div id=\"note\">");
		sb.append("<hr class=\"note_hr\" />");
		boolean first = true;
		for (NoteInline note : getNoteList()) {
			if (!first) { sb.append(DomainParameter.getDomainParameter().getLineSeparator()); } else { first = false; }
			sb.append(note.toNoteHtmlString());
		}
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * @return
	 */
	public Set<String> getNonExistsPageSet() {
		return nonexistspageset;
	}

	/**
	 * @return
	 */
	public Set<String> getPageSet() {
		return pageset;
	}
	
	/**
	 * @return
	 */
	public List<HeadlineBlock> getContentsList() {
		return contentslist;
	}

	/**
	 * @return
	 */
	public List<NoteInline> getNoteList() {
		return notelist;
	}

	/**
	 * @param pagename
	 */
	public boolean checkPage(String pagename) {
		assert (pagename != null);
		getPageSet().add(pagename);
		if (allpageset.contains(pagename)) {
			return true;
		} else {
			getNonExistsPageSet().add(pagename);
			return false;
		}
	}


}
