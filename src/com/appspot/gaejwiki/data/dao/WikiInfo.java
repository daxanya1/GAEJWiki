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


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.jdo.JDOException;
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
public class WikiInfo {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
	@Persistent
    private String pagename;
	
	@Persistent
    private Integer version;
	
	@Persistent
    private Integer todaycounter;
	
	@Persistent
    private Integer yesterdaycounter;
    
	@Persistent
    private Integer totalcounter;
    
	@Persistent
    private Date counterupdatedate;
    
	@Persistent
    private Date updatedate;
    
    



	public Key getKey() {
		return key;
	}





	public void setKey(Key key) {
		this.key = key;
	}





	public String getPagename() {
		return pagename;
	}





	public void setPagename(String pagename) {
		this.pagename = pagename;
	}





	public Integer getVersion() {
		return version;
	}





	public void setVersion(Integer version) {
		this.version = version;
	}





	public Integer getTodaycounter() {
		return todaycounter;
	}





	public void setTodaycounter(Integer todaycounter) {
		this.todaycounter = todaycounter;
	}





	public Integer getYesterdaycounter() {
		return yesterdaycounter;
	}





	public void setYesterdaycounter(Integer yesterdaycounter) {
		this.yesterdaycounter = yesterdaycounter;
	}





	public Integer getTotalcounter() {
		return totalcounter;
	}





	public void setTotalcounter(Integer totalcounter) {
		this.totalcounter = totalcounter;
	}





	public Date getCounterupdatedate() {
		return counterupdatedate;
	}





	public void setCounterupdatedate(Date counterupdatedate) {
		this.counterupdatedate = counterupdatedate;
	}





	public Date getUpdatedate() {
		return updatedate;
	}





	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}





	public static class Util {
    
		public void saveData(WikiInfo data) {
	        PersistenceManager pm = PMF.get().getPersistenceManager();
	        try{
	            pm.makePersistent(data);
	        } finally {
	            pm.close();
	        }
	    }
		
		public Key makeKey(String pagename) {
	    	KeyFactory.Builder kb = new KeyFactory.Builder(RootEntity.class.getSimpleName(), EntityKey.WikiInfo.toString());
	    	kb.addChild(WikiInfo.class.getSimpleName(), pagename);
			return kb.getKey();
		}

		public WikiInfo loadData(Key key) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				WikiInfo data = pm.getObjectById(WikiInfo.class, key);
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


		/**
		 * WikiInfoを読み込むと同時に、counterをインクリメントする
		 * トランザクション対象
		 * インクリメント対象は、today及びtotalとする。
		 * counterupdatedateを見て、昨日になっている場合は、todayをyesterdayとし、todayは１にする
		 * @param makeKey
		 * @return
		 */
		public WikiInfo loadAndIncrementData(Key key) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
	        try{
	            pm.currentTransaction().begin();
				WikiInfo info = pm.getObjectById(WikiInfo.class, key);
				if (isNextDay(info.getCounterupdatedate())) {
					info.setYesterdaycounter(info.getTodaycounter());
					info.setTodaycounter(1);
				} else {
					info.setTodaycounter(info.getTodaycounter() + 1);
				}
				info.setTotalcounter(info.getTotalcounter() + 1);
		    	Calendar cal = Calendar.getInstance();
		    	info.setUpdatedate(cal.getTime());
		    	info.setCounterupdatedate(cal.getTime());
		    	pm.makePersistent(info);
				pm.currentTransaction().commit();
				return info;
	        } catch (Exception e) {
	        	return null;
	        } finally {
	        	if(pm.currentTransaction().isActive()){
	    			pm.currentTransaction().rollback();
	    		}
	        	pm.close();
	        }
		}
		
		/**
		 * 引数のdateが今日と比べて、１日以上前かどうか調べる
		 * @param date
		 * @return dateより現在が後で、日が異なる場合、true それ以外はfalse
		 */
		public boolean isNextDay(Date date) {
	    	Calendar cal = Calendar.getInstance();
	    	Date nowdate = cal.getTime();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
	        DomainParameter domainparam = DomainParameter.getDomainParameter();
	        String timezone = domainparam.get(DomainParameter.TIMEZONE);
	        if (timezone != null) {
		        sdf1.setTimeZone(TimeZone.getTimeZone(timezone));
	        }
	    	
	    	return (nowdate.after(date) && (!sdf1.format(date).equals(sdf1.format(nowdate)))) ? true : false;
			
		}
    }
}