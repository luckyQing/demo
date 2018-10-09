package com.liyulin.skills.xml;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析XML文件
 */
public class XMLSAX extends DefaultHandler{

	// 存储解析的对象
	private Map<String, String> map = null;
	
	public XMLSAX(){
		map = new HashMap<String, String>();
	}
	
	/**
	 * 当开始读取XML文档时 
	 */
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	/**
	 * 当开始读取XML元素时 
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
	}

	/**
	 * 当读取XML文档结束时 
	 */
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	/**
	 * 当读取XML元素结束时 
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
	}

	/**
	 * 当读取XML文档内容时 
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
	}
	
}
