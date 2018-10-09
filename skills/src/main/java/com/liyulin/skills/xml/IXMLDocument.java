package com.liyulin.skills.xml;

/**
 * 操作XML接口
 *
 * @author liyulin
 * 
 * @version 1.0 2013-9-20 上午12:15:35
 */
public interface IXMLDocument {

	/**
	 * 创建XML
	 * 
	 * @param filePath xml文件路径
	 */
	public void createXml(String filePath);

	/**
	 * 解析XML
	 * 
	 * @param filePath xml文件路径
	 */
	public void parseXml(String filePath);
	
}
