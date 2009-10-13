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

package com.appspot.gaejwiki.data.common;

import java.io.UnsupportedEncodingException;

import com.google.appengine.api.datastore.Blob;

/**
 *
 * @author Ryuichi Danno
 */
public class DataUtils {

	public static final String CHARCODE = "UTF-8";
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public Blob stringToBlob(String str) {
		try {
			return new Blob(str.getBytes(CHARCODE));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param blob
	 * @return
	 */
	public String toBlogString(Blob blob) {
		try {
			return new String(blob.getBytes(), CHARCODE);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
}
