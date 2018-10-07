package com.liyulin.skills.reflect.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class Test {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Unsafe unsafe = UnsafeUtils.getUnsafe();
		// 实例字段
		VO vo = new VO();  
		long aoffset = unsafe.objectFieldOffset(VO.class.getDeclaredField("a"));  
		System.out.println("aoffset="+aoffset);
		int va = unsafe.getInt(vo, aoffset);  
		System.out.println("va="+va);  
		
		// 静态字段
		Field eField = VO.class.getDeclaredField("e");  
		Object base = unsafe.staticFieldBase(eField);  
		long offset = unsafe.staticFieldOffset(eField);  
		System.out.println("ve="+unsafe.getInt(base, offset));
		
		// 数组
		// 数组第一个元素的偏移地址,即数组头占用的字节数 
		long intOffset = unsafe.arrayBaseOffset(vo.intArr.getClass());
		// 数组中每个元素占用的大小
		long indexScale = unsafe.arrayIndexScale(vo.intArr.getClass());
		for(int i=0; i<vo.intArr.length; i++){
			System.out.println(unsafe.getInt(vo.intArr, intOffset+indexScale*i));
		}
	}
	
}
