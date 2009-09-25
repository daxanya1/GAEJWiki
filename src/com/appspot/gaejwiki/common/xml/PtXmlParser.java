package com.appspot.gaejwiki.common.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import org.fb.xml.parser.BitXmlParserI;


public class PtXmlParser  implements BitXmlParserI {

	private static final Logger logger = Logger.getLogger(PtXmlParser.class.getName());

	private final List<PtParam> list = new ArrayList<PtParam>();
	private final Stack<String> nowstack = new Stack<String>();
	
	private PtParam nowparam = null;
	
	public final List<PtParam> getList() {
		return list;
	}
	
	@Override
	public void endTag(String arg0) {
		if (arg0 == null) {
			return;
		}
		// stackÇÃç≈èâÇå©ÇƒÅAìØÇ∂Ç≈Ç†ÇÍÇŒÇªÇÃÇ‹Ç‹î≤Ç≠
		// à·Ç¡ÇƒÇ¢ÇÍÇŒÅAìØÇ∂Ç‡ÇÃÇíTÇµÇƒî≤Ç≠
		String first = null;
		while (!arg0.equals(first)) {
			if (nowstack.size() == 0) {
				break;
			}
			first = nowstack.pop();
		}
	}

	@Override
	public void property(String arg0, String arg1) {
		if (nowparam == null) {
			logger.warning("property: nowparam null");
			return;
		}
		
		if (arg0 == null || arg0.length() == 0 || "/".equals(arg0)) {
			return;
		}
		
		String nowtag = nowstack.firstElement();
		
		if ("param".equals(nowtag)) {
			if ("key".equals(arg0)) {
				nowparam.setMapKey(arg1, null);
			}
		} else if ("phtml".equals(nowtag)) {
			if ("name".equals(arg0)) {
				nowparam.setName(arg1);
			}
		}
	}

	@Override
	public void startTag(String arg0) {
		nowstack.push(arg0);
		if ("phtml".equals(arg0)) {
			nowparam = new PtParam();
			list.add(nowparam);
		}
	}

	@Override
	public void text(String arg0) {
		// ñ≥éãÇ∑ÇÈ
	} 

}
