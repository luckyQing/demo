package com.liyulin.design.patterns.singleton;

import java.util.Map;
import java.util.HashMap;

/**
 * 缓存模拟；单例模式的变形：三例
 * 
 * @author liyulin
 * @version 1.0 2013-9-6 下午7:42:30
 */
public class Cache {

	// 1、定义缓存数据的容器
	private static Map<Object, Object> map = new HashMap<Object, Object>();

	private Cache() {

	}

	// 模拟缓存
	public static Cache getCache() {
		// 2、到缓存里面查找需要使用的数据
		Cache cache = (Cache) map.get("data");

		// 3、如果找不到
		if (cache == null) {
			// 3.1、就新建一个数据（或者获取一个数据）
			cache = new Cache();
			// 3.2、然后把新建的数据设置到缓存中
			map.put("data", cache);
		}

		// 4、如果找到了就直接使用
		return cache;
	}
	
	
	/*---------------------------------- 控制三个实例 ----------------------------------*/ 
	// 用来记录当前正在使用哪一个实例，初始值为1
	private static int num = 1;
	// 实例的总个数为3个
	private static int count = 3;
	
	public static Cache getCache2() {
		Cache cache = (Cache) map.get(num);

		if (cache == null) {
			cache = new Cache();
			map.put(num, cache);
		}

		num++;
		if(num > count){
			num = 1;
		}
		return cache;
	}
}
