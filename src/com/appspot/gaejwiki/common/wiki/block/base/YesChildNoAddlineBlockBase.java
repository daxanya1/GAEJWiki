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
package com.appspot.gaejwiki.common.wiki.block.base;

import com.appspot.gaejwiki.common.wiki.block.WikiObjectBlockI;



/**
 * WikiObject
 * 子供にはなれるが行追加ができない
 * @author daxanya
 * 
 * --
 * 

 *
 * --
 */
public abstract class YesChildNoAddlineBlockBase implements WikiObjectBlockI {

	private WikiObjectBlockI parent = null;
	private String rawdata = new String("");
	
	@Override
	public void addLine(String str) {
		rawdata = str;
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
	public void addChildBlock(WikiObjectBlockI wikiobject) {
		// なにもしない。
	}

	@Override
	public WikiObjectBlockI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectBlockI wikiobject) {
		parent = wikiobject;
	}


	@Override
	public String toDebugString() {
		return rawdata + "\n";
	}
	
	
	@Override
	public int getLevel() {
		// level設定はないので、必ず-1を返す
		return -1;
	}

	protected String getRawData() {
		return rawdata;
	}

}
