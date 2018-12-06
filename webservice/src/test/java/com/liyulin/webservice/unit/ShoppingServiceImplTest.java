package com.liyulin.webservice.unit;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.liyulin.webservice.request.ProductReqBody;
import com.liyulin.webservice.response.OrderDto;
import com.liyulin.webservice.service.IShoppingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingServiceImplTest {

    // 接口地址
	private String address = "http://127.0.0.1/shopping?wsdl";
	
	@Test
	public void testProxyClient() {
		try {
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(IShoppingService.class);
            // 创建一个代理接口实现
            IShoppingService shoppingService = (IShoppingService) jaxWsProxyFactoryBean.create();
            // 数据准备
            // 调用代理接口的方法调用并返回结果
            ProductReqBody productReqBody = new ProductReqBody();
            productReqBody.setName("橘子");
            productReqBody.setBuyCount(3);
            productReqBody.setPrice(20L);
            OrderDto result = shoppingService.buy(productReqBody);
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
		// 需要密码的情况需要加上用户名和密码
		// client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
		// PASS_WORD));
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			ProductReqBody productReqBody = new ProductReqBody();
            productReqBody.setName("橘子");
            productReqBody.setBuyCount(3);
            productReqBody.setPrice(20L);
			Object[] objects1 = client.invokeWrapped("buy", productReqBody);
			log.info("返回数据：{}", JSON.toJSONString(objects1));
		} catch (java.lang.Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}