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

package com.appspot.gaejwiki.data.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 *
 * @author Ryuichi Danno
 */
public class WikiInfoTest {

	@Test
	public void testWikiInfoUtilIsNextDay1() {
		WikiInfo.Util util = new WikiInfo.Util();
    	Calendar cal = Calendar.getInstance();
    	Date nowdate = cal.getTime();
		assertFalse(util.isNextDay(nowdate));
	}
	
	@Test
	public void testWikiInfoUtilIsNextDay2() {
		WikiInfo.Util util = new WikiInfo.Util();
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, -1);
    	Date nowdate = cal.getTime();
		assertTrue(util.isNextDay(nowdate));
	}

	@Test
	public void testWikiInfoUtilIsNextDay3() {
		WikiInfo.Util util = new WikiInfo.Util();
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, +1);
    	Date nowdate = cal.getTime();
		assertFalse(util.isNextDay(nowdate));
	}


}
