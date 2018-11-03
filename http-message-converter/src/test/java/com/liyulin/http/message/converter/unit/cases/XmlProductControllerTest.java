package com.liyulin.http.message.converter.unit.cases;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liyulin.http.message.converter.enums.ReturnCodeEnum;
import com.liyulin.http.message.converter.unit.base.BaseUnitTest;
import com.liyulin.http.message.converter.xml.dto.XmlProductDto;
import com.liyulin.http.message.converter.xml.dto.XmlResult;

public class XmlProductControllerTest extends BaseUnitTest {
	
	@Test
	public void testXml() throws Exception {
		XmlProductDto xmlProductDto = new XmlProductDto();
		xmlProductDto.setId(1000L);
		xmlProductDto.setName("手机");
		xmlProductDto.setPrice(288800);

		XmlResult<XmlProductDto> result = super.postXml("/xml", xmlProductDto, new TypeReference<XmlResult<XmlProductDto>>() {});

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getCode()).isEqualTo(ReturnCodeEnum.SUCCESS.getCode());
		
		XmlProductDto resultXmlProductDto = result.getData();
		Assertions.assertThat(resultXmlProductDto.getId()).isEqualTo(xmlProductDto.getId());
		Assertions.assertThat(resultXmlProductDto.getName()).isEqualTo(xmlProductDto.getName());
		Assertions.assertThat(resultXmlProductDto.getPrice()).isEqualTo(xmlProductDto.getPrice());
	}
	
}
