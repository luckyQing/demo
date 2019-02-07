package com.liyulin.webservice.service;

import javax.jws.WebService;

import com.liyulin.webservice.request.ProductReqBody;
import com.liyulin.webservice.response.OrderRespBody;

@WebService
public interface IShoppingService {

	OrderRespBody buy(ProductReqBody productReqBody);

}