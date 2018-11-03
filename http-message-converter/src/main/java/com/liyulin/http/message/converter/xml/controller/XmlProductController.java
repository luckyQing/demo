package com.liyulin.http.message.converter.xml.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.http.message.converter.enums.ReturnCodeEnum;
import com.liyulin.http.message.converter.xml.dto.XmlProductDto;
import com.liyulin.http.message.converter.xml.dto.XmlResult;

@RestController
public class XmlProductController {

	@PostMapping(value = "/xml")
	public XmlResult<XmlProductDto> xml(@RequestBody XmlProductDto xmlProductDto) {
		return new XmlResult<>(ReturnCodeEnum.SUCCESS, xmlProductDto);
	}

}
