package com.liyulin.httpxml.xml.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.httpxml.xml.dto.XmlProductDto;
import com.liyulin.httpxml.xml.dto.XmlResultT;

@RestController
public class XmlProductController {

	@PostMapping(value = "/xml", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public XmlResultT<XmlProductDto> test(@RequestBody XmlProductDto xmlProductDto) {
		return new XmlResultT<>("0000", "成功", xmlProductDto);
	}

}
