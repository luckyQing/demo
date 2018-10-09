package com.liyulin.design.patterns.prototype;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 原型模式
 *
 * @author liyulin
 * @version 1.0 2014-10-11 上午10:58:17
 */
@Data
public class PrototypeDemo implements Cloneable {

	private String content;
	private List<String> contents;

	/**
	 * 覆盖Object类的clone()方法
	 */
	public PrototypeDemo clone(){
		try{
			PrototypeDemo prototypeDemo = (PrototypeDemo)super.clone();
			List<String> newContents = new ArrayList<String>();
			
			for(String content:this.getContents()){
				newContents.add(content);
			}
			
			prototypeDemo.setContents(newContents);
			return prototypeDemo;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
