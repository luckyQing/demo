package com.liyulin.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebServiceClient {

	public static void main(String[] args) {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://127.0.0.1/user?wsdl");
		// 需要密码的情况需要加上用户名和密码
		// client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
		// PASS_WORD));
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			Object[] objects1 = client.invoke("getUser", "123");
			log.info("返回数据：{}", JSON.toJSONString(objects1));
			
			Object[] objects2 = client.invoke("getUserName", "123");
			log.info("返回数据：{}", JSON.toJSONString(objects2));
		} catch (java.lang.Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}