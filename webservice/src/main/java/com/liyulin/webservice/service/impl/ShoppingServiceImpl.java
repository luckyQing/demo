package com.liyulin.webservice.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.liyulin.webservice.request.ProductReqBody;
import com.liyulin.webservice.response.OrderDto;
import com.liyulin.webservice.service.IShoppingService;

@WebService(
		// 对外发布的服务名
		serviceName = "ShoppingService",
		// 指定你想要的名称空间，通常使用使用包名反转
		targetNamespace = "http://service.webservice.liyulin.com/",
		// 服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
		endpointInterface = "com.liyulin.webservice.service.IShoppingService")
@Service
public class ShoppingServiceImpl implements IShoppingService {

	@Override
	public OrderDto buy(ProductReqBody productReqBody) {
		OrderDto orderDto = new OrderDto();
		orderDto.setName(productReqBody.getName());
		orderDto.setPrice(productReqBody.getPrice());
		orderDto.setBuyCount(productReqBody.getBuyCount());

		return orderDto;
	}

}