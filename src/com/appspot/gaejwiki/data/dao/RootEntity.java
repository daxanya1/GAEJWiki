package com.appspot.gaejwiki.data.dao;


import java.util.ConcurrentModificationException;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class RootEntity {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    @Persistent
    private Long count;

    /**
     * コンストラクタ
     * 
     */
    public RootEntity() {
        this.count = 0L;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    
    public static class Util {
    
		public long getNextCount(EntityKey entrykey) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			long count = 0;
	        try{
	            pm.currentTransaction().begin();
	            Key key = makeKey(entrykey.toString());
	            RootEntity root = pm.getObjectById(RootEntity.class, key);
				count = root.getCount()+1;
				root.setCount(count);
				pm.makePersistent(root);
				pm.currentTransaction().commit();
	        } catch (Exception e) {
			   	return -1;
	        } finally {
	        	if(pm.currentTransaction().isActive()){
	    			pm.currentTransaction().rollback();
	    		}
	        	pm.close();
	        }
	        return count;
		}
		
		public void saveData(Key key) {
	        RootEntity rootentity = new RootEntity();
	        rootentity.setKey(key);
	        PersistenceManager pm = PMF.get().getPersistenceManager();
	        try{
	            pm.makePersistent(rootentity);
	        } finally {
	            pm.close();
	        }
	    }
		
		public Key makeKey(String id) {
			return KeyFactory.createKey(RootEntity.class.getSimpleName(), id);
		}

		public RootEntity loadData(Key key) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				RootEntity data = pm.getObjectById(RootEntity.class, key);
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
		
		public void deleteTable(EntityKey entitykey) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			try {
				Query query = pm.newQuery(Class.forName("miniload.dao." + entitykey.toString()));
				try {
					List<Object> list = (List<Object>)query.execute();
		            pm.deletePersistentAll(list);
				} finally {
					query.closeAll();
				}
			} catch (ClassNotFoundException e) {
				assert(false) : "deleteTable ClassNotFoundException";
			} finally {
	            pm.close();
			}
		}
    }
}