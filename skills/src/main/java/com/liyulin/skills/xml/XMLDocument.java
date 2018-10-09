package com.liyulin.skills.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOM操作XML实现类
 * 
 * @author liyulin
 * @version 1.0 2013-9-20 上午12:15:35 
 */
public class XMLDocument implements IXMLDocument {

	/**
	 * DOM创建XML
	 * 
	 * @param filePath xml文件路径
	 */
	public void createXml(String filePath){
		// 创建DOM文档工厂实例
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = null;
		try {
			// 创建DOM文档解析器
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		// 创建Document对象
		Document document = builder.newDocument();
		
		// 创建根节点
		Element root = document.createElement("Students");
		document.appendChild(root);
		/*======================== 创建第一个子节点 ========================*/
		// 创建子节点
		Element student1 = document.createElement("Student");
		// 创建姓名节点
		Element sname1 = document.createElement("name");
		Node snameText1 = document.createTextNode("李四");
		sname1.appendChild(snameText1);
		sname1.setAttribute("sid", "1001");
		// 创建年龄节点
		Element sage1 = document.createElement("age");
		Node sageText1 = document.createTextNode("21");
		sage1.appendChild(sageText1);
		// 创建年龄性别
		Element ssex1 = document.createElement("sex");
		Node ssexText1 = document.createTextNode("女");
		ssex1.appendChild(ssexText1);
		
		student1.appendChild(sname1);
		student1.appendChild(sage1);
		student1.appendChild(ssex1);
		
		/*======================== 创建第二个子节点 ========================*/
		// 创建子节点
		Element student2 = document.createElement("Student");
		// 创建姓名节点
		Element sname2 = document.createElement("name");
		Node snameText2 = document.createTextNode("张三");
		sname2.setAttribute("sid", "1002");
		sname2.appendChild(snameText2);
		// 创建年龄节点
		Element sage2 = document.createElement("age");
		Node sageText2 = document.createTextNode("20");
		sage2.appendChild(sageText2);
		// 创建年龄性别
		Element ssex2 = document.createElement("sex");
		Node ssexText2 = document.createTextNode("男");
		ssex2.appendChild(ssexText2);
		
		student2.appendChild(sname2);
		student2.appendChild(sage2);
		student2.appendChild(ssex2);
		/*======================== 创建第三个子节点 ========================*/
		// 创建子节点
		Element student3 = document.createElement("Student");
		// 创建姓名节点
		Element sname3 = document.createElement("name");
		Node snameText3 = document.createTextNode("王五");
		sname3.setAttribute("sid", "1003");
		sname3.appendChild(snameText3);
		// 创建年龄节点
		Element sage3 = document.createElement("age");
		Node sageText3 = document.createTextNode("25");
		sage3.appendChild(sageText3);
		// 创建年龄性别
		Element ssex3 = document.createElement("sex");
		Node ssexText3 = document.createTextNode("女");
		ssex3.appendChild(ssexText3);
		
		student3.appendChild(sname3);
		student3.appendChild(sage3);
		student3.appendChild(ssex3);
		
		// 将每一个学生节点添加到根节点中
		root.appendChild(student1);
		root.appendChild(student2);
		root.appendChild(student3);
		
		try {
			// 创建字符输出流
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(filePath));
			// 将字符输出流转成结果流
			StreamResult result = new StreamResult(printWriter);
			
			// 创建转换工厂实例
			TransformerFactory tFactory = TransformerFactory.newInstance();
			// 缩进（jdk1.5以上才支持）
			tFactory.setAttribute("indent-number", new Integer(4));
			
			try {
				// 创建转换解析器
				Transformer former = tFactory.newTransformer();
				// 换行
				former.setOutputProperty(OutputKeys.INDENT, "yes");
				// 设置编码
				former.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				
				// 将文档对象转换成XML源码
				DOMSource source = new DOMSource(document);
				// 将XML源码转换成结果输出流
				try {
					former.transform(source, result);
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * DOM解析XML
	 * 
	 * @param filePath xml文件路径
	 */
	public void parseXml(String filePath){
		// 创建DOM文档工厂实例
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				
		DocumentBuilder builder = null;
		try {
			// 创建DOM文档解析器
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		// 创建Document对象
		Document document = null;
		try {
			document = builder.parse(filePath);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 获得所有节点（根节点）
		NodeList nodeList = document.getChildNodes();
		// 获得根节点
		Node studentsList = nodeList.item(0);
		NodeList studentList = studentsList.getChildNodes();
		
		// 循环根节点下的所有子节点
		for (int i = 0, studentListSize = studentList.getLength(); i < studentListSize; i++) {
			Node student = studentList.item(i);
			// 得到student下所有子节点（包括空节点）
			NodeList objs = student.getChildNodes();
			for (int j = 0, objsSize = objs.getLength(); j < objsSize; j++) {
				// 打印节点名称和文本内容
				System.out.println(objs.item(j).getNodeName()+":"+objs.item(j).getTextContent());
			}
		}
	}

}
