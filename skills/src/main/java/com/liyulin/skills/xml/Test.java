package com.liyulin.skills.xml;

public class Test {

	public static void main(String[] args) {
		IXMLDocument xml = new XMLDom4j();
		// xml.createXml("d:/xmlDOM.xml");
		// xml.createXml("d:/xmlDom4j.xml");
		xml.parseXml("d:/xmlDom4j.xml");
	}

}
