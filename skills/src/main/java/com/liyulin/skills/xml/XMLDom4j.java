package com.liyulin.skills.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Dom4j操作XML实现类
 * 注意：需要导入dom4j-1.6.1.jar和jaxen-1.1.1.jar
 * 
 * @author liyulin
 * @version 1.0 2013-9-20 上午12:15:35 
 */
public class XMLDom4j implements IXMLDocument {

	/**
	 * Dom4j创建XML
	 * 
	 * @param filePath xml文件路径
	 */
	@SuppressWarnings("static-access")
	public void createXml(String filePath) {
		// 创建Document对象
		Document document = DocumentHelper.createDocument();
		// 创建根节点
		Element employees = document.addElement("employees");
		
		// 创建第一个员工
		Element employee1 = employees.addElement("employee");
		// 创建姓名、年龄、性别节点
		Element employeeName1 = employee1.addElement("name");
		employeeName1.addAttribute("empNo", "E1001");
		employeeName1.setText("张三");
		Element employeeAge1 = employee1.addElement("age");
		employeeAge1.setText("20");
		Element employeeSex1 = employee1.addElement("sex");
		employeeSex1.setText("男");
		
		// 创建第二个员工
		Element employee2 = employees.addElement("employee");
		// 创建姓名、年龄、性别节点
		Element employeeName2 = employee2.addElement("name");
		employeeName2.addAttribute("empNo", "E1002");
		employeeName2.setText("李四");
		Element employeeAge2 = employee2.addElement("age");
		employeeAge2.setText("22");
		Element employeeSex2 = employee2.addElement("sex");
		employeeSex2.setText("女");
		
		// 创建转换流
		OutputFormat format = new OutputFormat().createPrettyPrint();
		PrintWriter printWriter = null;
		try {
			// 创建字符输出流
			printWriter = new PrintWriter(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 创建XML输出流
		XMLWriter writer = new XMLWriter(printWriter, format);
		try {
			writer.write(document);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

	/**
	 * Dom4j解析XML
	 * 
	 * @param filePath xml文件路径
	 */
	@SuppressWarnings("unchecked")
	public void parseXml(String filePath) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			// 得到Document对象
			document = reader.read(filePath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		/*==========================================================================================*/
		// 得到固定节点的集合
		// List<Element> list = root.selectNodes("//employee");
		// for (int i = 0, listSize = list.size(); i < listSize; i++) {
		// Element element = list.get(i);
		// // 获得固定节点中指定属性的值
		// System.out.println(element.element("name").attributeValue("empNo"));
		// // 获得固定节点的文本值
		// System.out.println(element.element("name").getText());
		// System.out.println(element.element("age").getText());
		// System.out.println(element.element("sex").getText());
		// }
		// 得到迭代器
		Iterator<Element> iterator = root.elementIterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			System.out.println(element.getName());
			Iterator<Element> iter = element.elementIterator();
			while(iter.hasNext()){
				Element elementChild = iter.next();
				// 获得empNo的值
				if(elementChild.getName().equals("name")){
					System.out.println("员工编号是："+elementChild.attributeValue("empNo"));
				}
				System.out.println("子节点名称"+elementChild.getName()+":"+elementChild.getText());
			}
		}
		
	}

}

