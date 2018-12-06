package com.liyulin.webservice.service;

import javax.jws.WebService;

import com.liyulin.webservice.request.ProductReqBody;
import com.liyulin.webservice.response.OrderDto;

@WebService
public interface IShoppingService {

	OrderDto buy(ProductReqBody productReqBody);

}