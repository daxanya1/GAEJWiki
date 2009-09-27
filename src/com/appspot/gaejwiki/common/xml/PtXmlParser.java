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
	
	public final String TAG_PHTML = "phtml";
	public final String TAG_PARAM = "param";
	public final String ATTRIVUTE_KEY = "key";
	public final String ATTRIVUTE_NAME = "name";
	public final String PARAMVALUE = "_";
	
	public final List<PtParam> getList() {
		return list;
	}
	
	@Override
	public void endTag(String arg0) {
		if (arg0 == null) {
			return;
		}
		// stack‚ÌÅ‰‚ðŒ©‚ÄA“¯‚¶‚Å‚ ‚ê‚Î‚»‚Ì‚Ü‚Ü”²‚­
		// ˆá‚Á‚Ä‚¢‚ê‚ÎA“¯‚¶‚à‚Ì‚ð’T‚µ‚Ä”²‚­
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
		
		String nowtag = nowstack.lastElement();
		
		if (TAG_PARAM.equals(nowtag)) {
			if (ATTRIVUTE_KEY.equals(arg0)) {
				String param = new Util().trimDoubleQuote(arg1);
				nowparam.setMapKey(param, PARAMVALUE);
			}
		} else if (TAG_PHTML.equals(nowtag)) {
			if (ATTRIVUTE_NAME.equals(arg0)) {
				String param = new Util().trimDoubleQuote(arg1);
				nowparam.setName(param);
			}
		}
	}

	@Override
	public void startTag(String arg0) {
		nowstack.push(arg0);
		if (TAG_PHTML.equals(arg0)) {
			nowparam = new PtParam();
			list.add(nowparam);
		}
	}

	@Override
	public void text(String arg0) {
		// –³Ž‹‚·‚é
	} 
	
	static public class Util {
		
		public String trimDoubleQuote(String param) {
			if (param != null && param.length() > 0) {
				if (param.charAt(0) == '"' && param.charAt(param.length()-1) == '"') {
					param = param.substring(1, param.length()-1);
				}
			}
			return param;
		}
	}

}
