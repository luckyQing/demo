package com.liyulin.webservice.cfx.service;

import javax.jws.WebService;

import com.liyulin.webservice.cfx.request.ProductReqBody;
import com.liyulin.webservice.cfx.response.OrderRespBody;

@WebService
public interface IShoppingService {

	OrderRespBody buy(ProductReqBody productReqBody);

}