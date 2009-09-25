package com.appspot.gaejwiki.common.xml;

import java.util.HashMap;
import java.util.Map;

public class PtParam {

	private String name;
	private final Map<String, String> map = new HashMap<String, String>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public final Map<String, String> getMap() {
		return map;
	}
	public void setMapKey(String key, String value) {
		map.put(key, value);
	}
	
	
}
