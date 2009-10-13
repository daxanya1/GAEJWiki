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

import com.appspot.gaejwiki.data.common.DataUtils;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class WikiData {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
	@Persistent
    private Blob wikidata;
    
	@Persistent
    private Blob htmldata;
    
	@Persistent
    private Date updatedate;
    
    
    public Key getKey() {
		return key;
	}


	public void setKey(Key key) {
		this.key = key;
	}
	

	public Blob getWikidata() {
		return wikidata;
	}


	public void setWikidata(Blob wikidata) {
		this.wikidata = wikidata;
	}


	public Blob getHtmldata() {
		return htmldata;
	}


	public void setHtmldata(Blob htmldata) {
		this.htmldata = htmldata;
	}


	public Date getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getHtmldataString() {
		return new DataUtils().toBlogString(getHtmldata());
	}

	public String getWikidataString() {
		return new DataUtils().toBlogString(getWikidata());
	}

	/**
	 * 
	 */
	public void setUpdatedateNow() {
    	setUpdatedate(Calendar.getInstance().getTime());
	}

	public void setHtmldataString(String htmldatastr) {
		setHtmldata(new DataUtils().stringToBlob(htmldatastr));
	}

	public void setWikidataString(String wikidatastr) {
		setWikidata(new DataUtils().stringToBlob(wikidatastr));
	}


	public static class Util {
    
		public void saveData(WikiData data) {
	        PersistenceManager pm = PMF.get().getPersistenceManager();
	        try{
	            pm.makePersistent(data);
	        } finally {
	            pm.close();
	        }
	    }
		
		public Key makeKey(Key parentkey, Integer version) {
	    	KeyFactory.Builder kb = new KeyFactory.Builder(WikiInfo.class.getSimpleName(), EntityKey.WikiData.toString());
	    	kb.addChild(WikiData.class.getSimpleName(), parentkey.toString() + version.toString());
			return kb.getKey();
		}

		public WikiData loadData(Key key, boolean wikiflag, boolean htmlflag) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				WikiData data = pm.getObjectById(WikiData.class, key);
				if (htmlflag) {
					data.getHtmldata();
				}
				if (wikiflag) {
					data.getWikidata();
				}
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
			return (loadData(key, false, false) != null);
		}
		
    }

}