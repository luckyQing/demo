package com.liyulin.webservice.controller;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.webservice.client.shopping.IShoppingService;
import com.liyulin.webservice.client.shopping.OrderRespBody;
import com.liyulin.webservice.client.shopping.ProductReqBody;
import com.liyulin.webservice.client.shopping.ShoppingService;
import com.liyulin.webservice.client.user.IUserService;
import com.liyulin.webservice.client.user.UserRespBody;
import com.liyulin.webservice.client.user.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WebserviceClientController {
	
	private URL userWsdlLocation = null;
	private URL shoppingWsdlLocation = null;
	
	@PostConstruct
	public void init() throws MalformedURLException {
		userWsdlLocation = new URL("http://127.0.0.1/user?wsdl");
		log.info("wsdlLocation={}", userWsdlLocation);
		
		shoppingWsdlLocation = new URL("http://127.0.0.1/shopping?wsdl");
		log.info("shoppingWsdlLocation={}", shoppingWsdlLocation);
	}
	
	@GetMapping("getUser")
	public UserRespBody getUser(String userId) {
		UserService service = new UserService(userWsdlLocation);
		IUserService userService = service.getUserServiceImplPort();
		return userService.getUser(userId);
	}
	
	@GetMapping("buy")
	public OrderRespBody buy(int buyCount, String name, long price) {
		ShoppingService service = new ShoppingService(shoppingWsdlLocation);
		IShoppingService shoppingService = service.getShoppingServiceImplPort();
		
		ProductReqBody productReqBody = new ProductReqBody();
		productReqBody.setBuyCount(buyCount);
		productReqBody.setName(name);
		productReqBody.setPrice(price);
		
		return shoppingService.buy(productReqBody);
	}
	
}