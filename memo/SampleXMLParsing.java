package org.fb.xml.parser.sample;

import org.fb.xml.parser.*;

public class SampleXMLParsing implements BitXmlParserI{

	static int tab=0;
	
	public static void main(String[] args){
		
		String xml="<T1 mm=110><T2 pco=44 mao=22 ola=9999>gocdsacdsacller</T2><TX/></T1><bau></bau>";
		BitXmlParser xParser1 = new BitXmlParser();
		xParser1.setListener( new SampleXMLParsing() );
		xParser1.parseSax( xml );
	}

	
	public void startTag(String tagName) {
		tab++;
		show("<"+tagName+">");
	}

	
	public void text(String text) {
		 tab++;
		show("-->"+text);
		 tab--;
	}
	
	public void endTag(String tagName) {
		show("</"+tagName+">");
		tab--;
	}
	
	public void show(String s){
		String a="";
		for (int i=0;i<tab;i++)
			a+="  ";
		System.out.println(a+s);
	}


	public void property(String pName, String pValue) {
		show("Prop:"+pName+" = "+pValue);
		
	}

	
}
