package com.appspot.gaejwiki.common.wiki.stub;

import java.util.List;

import com.appspot.gaejwiki.common.wiki.CsvBlock;
import com.appspot.gaejwiki.common.wiki.DefinedListBlock;
import com.appspot.gaejwiki.common.wiki.FormatedBlock;
import com.appspot.gaejwiki.common.wiki.HashBlock;
import com.appspot.gaejwiki.common.wiki.HeadlineBlock;
import com.appspot.gaejwiki.common.wiki.HorizonBlock;
import com.appspot.gaejwiki.common.wiki.UnnumberedListBlock;
import com.appspot.gaejwiki.common.wiki.ParagraphBlock;
import com.appspot.gaejwiki.common.wiki.QuotationBlock;
import com.appspot.gaejwiki.common.wiki.TableBlock;
import com.appspot.gaejwiki.common.wiki.WikiObjectBlockFactory;
import com.appspot.gaejwiki.common.wiki.base.WikiObjectI;

public class WikiObjectBlockFactoryForTest extends WikiObjectBlockFactory {
	
	private String toRawlistString(List<String> rawlist) {
		StringBuffer sb = new StringBuffer();
		for (String str : rawlist) {
			sb.append(str);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private String toChildlistString(List<WikiObjectI> childlist) {
		StringBuffer sb = new StringBuffer();
		for (WikiObjectI wikiobj : childlist) {
			sb.append("/c:");
			sb.append(wikiobj.toString());
			sb.append(":c/");
		}
		return sb.toString();
	}
	
	private String toDataString(String data) {
		return data + "\n";
	}
	
	protected WikiObjectI createParagraphBlock() {
		return new ParagraphBlock() {
			public String toString() {
				return toRawlistString(getRawlist());
			}
		};
	}
	
	protected WikiObjectI createFormatedBlock() {
		return new FormatedBlock() {
			public String toString() {
				return toRawlistString(getRawlist());
			}
		};
	}
	
	protected WikiObjectI createHeadlineBlock() {
		return new HeadlineBlock() {
			public String toString() {
				return toDataString(getData());
			}
		};
	}
	
	protected WikiObjectI createQuotationBlock() {
		return new QuotationBlock() {
			public String toString() {
				return toRawlistString(getRawlist()) + toChildlistString(getChildlist());
			}
		};
	}			

	protected WikiObjectI createListBlock() {
		return new UnnumberedListBlock() {
			public String toString() {
				return toRawlistString(getRawlist()) + toChildlistString(getChildlist());
			}
		};
	}			

	protected WikiObjectI createDefinedListBlock() {
		return new DefinedListBlock() {
			public String toString() {
				return toRawlistString(getRawlist()) + toChildlistString(getChildlist());
			}
		};
	}			

	protected WikiObjectI createCsvBlock() {
		return new CsvBlock() {
			public String toString() {
				return toDataString(getData());
			}
		};
	}			

	protected WikiObjectI createHashBlock() {
		return new HashBlock() {
			public String toString() {
				return toDataString(getData());
			}
		};
	}			

	protected WikiObjectI createHorizonBlock() {
		return new HorizonBlock() {
			public String toString() {
				return toDataString(getData());
			}
		};
	}			

	protected WikiObjectI createTableBlock() {
		return new TableBlock() {
			public String toString() {
				return toRawlistString(getRawlist());
			}
		};
	}			

}
