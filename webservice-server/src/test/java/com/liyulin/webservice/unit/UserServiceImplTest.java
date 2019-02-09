package com.liyulin.webservice.unit;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.liyulin.webservice.cfx.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImplTest {

	// 接口地址
	private String address = "http://127.0.0.1/user?wsdl";

	@Test
	public void testProxyClient() {
		try {
			// 代理工厂
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			// 设置代理地址
			jaxWsProxyFactoryBean.setAddress(address);
			// 设置接口类型
			jaxWsProxyFactoryBean.setServiceClass(IUserService.class);
			// 创建一个代理接口实现
			IUserService userService = (IUserService) jaxWsProxyFactoryBean.create();

			Client proxy= ClientProxy.getClient(userService);
			HTTPConduit conduit=(HTTPConduit)proxy.getConduit();
			HTTPClientPolicy policy=new HTTPClientPolicy();
			policy.setConnectionTimeout(1000);
			policy.setReceiveTimeout(1000);
			conduit.setClient(policy);

			// 数据准备
			String userId = "maple";
			// 调用代理接口的方法调用并返回结果
			String result = userService.getUserName(userId);
			log.info("返回结果：{}", result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Test
	public void testDynamicClient() {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(address);

		// HTTPConduit conduit=(HTTPConduit)client.getConduit();
		// HTTPClientPolicy policy=new HTTPClientPolicy();
		// policy.setConnectionTimeout(1000);
		// policy.setReceiveTimeout(1000);
		// conduit.setClient(policy);
		// 需要密码的情况需要加上用户名和密码
		// client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
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