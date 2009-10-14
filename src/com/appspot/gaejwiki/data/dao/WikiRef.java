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


import java.util.Calendar;
import java.util.Date;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.gaejwiki.common.text.TextUtils;
import com.appspot.gaejwiki.data.common.DataExecI;
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

	public void setUpdatedateNow() {
		setUpdatedate(Calendar.getInstance().getTime());
	}
	
	public String[] getRefdataStringArray() {
		return new TextUtils().parseData(getRefdata());
	}
	
	
	public static class Util {
    
		public static final String KEYHEADER_ALL = "all";
		public static final String KEYFOOTER_PAGE = "_page_%_";
		public static final String KEYFOOTER_INCOMINGLINK = "_income_%_";
		public static final String KEYFOOTER_LINK = "_link_%_";
		
		public static final Key ALLPAGEKEY = new Util().makeKey(WikiRef.Util.KEYHEADER_ALL, WikiRef.Util.KEYFOOTER_PAGE);
		
		/**
		 * Refからrefdataを取り出して処理して書き込むまでの一連の流れをトランザクションを使って行う
		 * @param key Dataref読み出しKey
		 * @param execdata 処理インターフェース
		 * @return トランザクション処理に成功したらtrue
		 */
		public boolean execTransaction(Key key, DataExecI execdata) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
	        try{
	            pm.currentTransaction().begin();
	            WikiRef ref = null;
	            try {
	            	ref = pm.getObjectById(WikiRef.class, key);
	            } catch (JDOObjectNotFoundException e) {
	            }
				if (ref == null) {
					ref = new WikiRef();
					ref.setKey(key);
					ref.setRefdata(execdata.exec(new String("")));
				} else {
					ref.setRefdata(execdata.exec(ref.getRefdata()));
				}
				ref.setUpdatedateNow();
		    	pm.makePersistent(ref);
				pm.currentTransaction().commit();
				return true;
	        } catch (Exception e) {
	        	return false;
	        } finally {
	        	if(pm.currentTransaction().isActive()){
	    			pm.currentTransaction().rollback();
	    		}
	        	pm.close();
	        }
		}
		
		
		public void saveData(WikiRef data) {
	        PersistenceManager pm = PMF.get().getPersistenceManager();
	        try{
	            pm.makePersistent(data);
	        } finally {
	            pm.close();
	        }
	    }
		
		public Key makeKey(String pagename, String footer) {
	    	KeyFactory.Builder kb = new KeyFactory.Builder(RootEntity.class.getSimpleName(), EntityKey.WikiRef.toString());
	    	kb.addChild(WikiRef.class.getSimpleName(), pagename + footer);
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

		public String[] getRefStringArray(Key key) {
			assert(key != null);
			
			WikiRef ref = loadData(key);
			if (ref == null) {
				return null;
			}
			return ref.getRefdataStringArray();
		}


		/**
		 * @param pagename
		 * @return
		 */
		public String[] getRefStringArrayRefIncoming(String pagename) {
			assert(pagename != null);
			return getRefStringArray(makeKey(pagename, KEYFOOTER_INCOMINGLINK));
		}


		/**
		 * @param refkey
		 */
		public void removeData(Key key) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				WikiRef data = pm.getObjectById(WikiRef.class, key);
				pm.deletePersistent(data);
			} catch (JDOObjectNotFoundException e) {
			} catch (Exception e) {
			} finally {
	            pm.close();
			}
		}
    }

}