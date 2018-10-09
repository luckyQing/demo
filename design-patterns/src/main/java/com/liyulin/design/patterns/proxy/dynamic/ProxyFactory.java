package com.liyulin.design.patterns.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyFactory {
	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	// 给目标对象生成代理对象
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						log.info("开始事务2");
						// 执行目标对象方法
						Object returnValue = method.invoke(target, args);
						log.info("提交事务2");
						return returnValue;
					}
				});
	}
}
