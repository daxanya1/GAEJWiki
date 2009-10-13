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


import java.util.Date;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.gaejwiki.domain.setting.DomainParameter;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class WikiRef {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
	@Persistent
    private String refdata;
	    
	@Persistent
    private Date updatedate;
    
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getRefdata() {
		return refdata;
	}

	public void setRefdata(String refdata) {
		this.refdata = refdata;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	
	
	public static class Util {
    
		public void saveData(WikiRef data) {
	        PersistenceManager pm = PMF.get().getPersistenceManager();
	        try{
	            pm.makePersistent(data);
	        } finally {
	            pm.close();
	        }
	    }
		
		public Key makeKey(String pagename) {
	    	KeyFactory.Builder kb = new KeyFactory.Builder(RootEntity.class.getSimpleName(), EntityKey.WikiRef.toString());
	    	kb.addChild(WikiRef.class.getSimpleName(), pagename);
			return kb.getKey();
		}

		public WikiRef loadData(Key key) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				WikiRef data = pm.getObjectById(WikiRef.class, key);
				return pm.detachCopy(data);
			} catch (JDOObjectNotFoundException e) {
				return null;
			} catch (Exception e) {
				return null;
			} finally {
	            pm.close();
			}
		}
		
		public boolean isExist(Key key) {
			return (loadData(key) != null);
		}

		public String[] parserRefData(String refdata) {
			assert(refdata != null);
			return refdata.split(DomainParameter.getDomainParameter().getLineSeparator());
		}
		
		/**
		 * refdataに、pagenameを追加する
		 * すでにrefdata内にpagenameがあればそのまま返す
		 * @param refdata
		 * @param pagename
		 * @return
		 */
		public String addRefData(String refdata, String pagename) {
			assert(refdata != null);
			assert(pagename != null);
			
			String separator = DomainParameter.getDomainParameter().getLineSeparator();
			String[] strlist = parserRefData(refdata);
			StringBuffer sb = new StringBuffer();
			boolean existsflag = false;
			for (String str: strlist) {
				if (str.length() == 0) {
					continue;
				}
				if (pagename.equals(str)) {
					existsflag = true;
				}
				sb.append(str);
				sb.append(separator);
			}
			if (!existsflag) {
				sb.append(pagename);
				sb.append(separator);
			}
			
			return sb.toString();
		}
    }
}