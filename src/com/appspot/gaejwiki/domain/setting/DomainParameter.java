package com.appspot.gaejwiki.domain.setting;

import java.util.HashMap;
import java.util.Map;

/**
 * 設定ファイルやJavaのシステムプロバティから取得できるパラメータを集約する
 * @author daxanya
 *
 */
public class DomainParameter {
	
	// --
	// singlton
	
	private static final DomainParameter domainparam = new DomainParameter();
	public static DomainParameter getDomainParameter() {
		return domainparam;
	}
	// --
	
	public static final String DOMAINURL = "domain";
	public static final String VIEWURL = "view";
	public static final String EDITURL = "edit";
	public static final String VIEWTEMPLATE = "viewtemplate";
	public static final String EDITTEMPLATE = "edittemplate";
	public static final String LINESEPARATOR = "line.separator";
	
	private Map<String, String> parammap = new HashMap<String, String>();
	
	private DomainParameter() {
		init();
	}
	
	private void init() {
		Sub sub = new Sub();
		sub.putMap(DOMAINURL, parammap);
		sub.putMap(VIEWURL, parammap);
		sub.putMap(EDITURL, parammap);
		sub.putMap(VIEWTEMPLATE, parammap);
		sub.putMap(EDITTEMPLATE, parammap);
		// parammap.put(LINESEPARATOR, System.getProperty(LINESEPARATOR));
		parammap.put(LINESEPARATOR, "\n");
	}
	
	public String get(String key) {
		return parammap.get(key);
	}
	
	/**
	 * JUnit専用のパラメータセッター
	 * @param key
	 * @param value
	 */
	public void putUnitTestOnly(String key, String value) {
		parammap.put(key, value);
	}
	
	public String getLineSeparator() {
		return get(LINESEPARATOR);
	}
	
	public static class Sub {
		
		public void putMap(String key, Map<String, String> map) {
			map.put(key, System.getProperty("com.appspot.gaejwiki." + key));
		}
	}


}
