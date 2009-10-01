package com.appspot.gaejwiki.common.wiki.inline.base;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.appspot.gaejwiki.common.wiki.inline.WikiInlineParser;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineFactory;
import com.appspot.gaejwiki.common.wiki.inline.WikiObjectInlineI;

public abstract class ParentableInlineBase implements WikiObjectInlineI {

	private WikiObjectInlineI parent = null;
	private String rawdata = null;
	private List<WikiObjectInlineI> childlist = null;
	
	@Override
	public WikiObjectInlineI getParent() {
		return parent;
	}

	@Override
	public void setParent(WikiObjectInlineI wikiobject) {
		parent = wikiobject;
	}
	
	@Override
	public void set(String str, WikiObjectInlineFactory factory) {
		rawdata = str;
		// 再帰処理を行う
		String line = new Sub().matchSet(rawdata);
		if (line != null) {
			childlist = new WikiInlineParser().parseInline(factory, line);
		}
	}

	protected List<WikiObjectInlineI> getChildList() {
		return childlist;
	}
	
	public abstract String getPattern();

	public class Sub {
		
		// 正規表現にかけて、必要な情報を取り出す
		public String matchSet(String str) {
			Pattern pattern = Pattern.compile(getPattern());
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				return (matcher.groupCount() >= 1) ? matcher.group(1) : null;
			}
			
			return null;
		}
	}

}
