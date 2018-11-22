package com.liyulin.webservice.service;

import javax.jws.WebService;

import com.liyulin.webservice.dto.ProductDto;
import com.liyulin.webservice.dto.Result;

@WebService
public interface IDemoService {
	
	Result<ProductDto> buy(ProductDto productDto);
	
}