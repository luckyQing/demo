package com.liyulin.http.message.converter.unit.cases;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
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

		XmlResult<XmlProductDto> result = super.postJson("/json", xmlProductDto, new TypeReference<XmlResult<XmlProductDto>>() {});

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getCode()).isEqualTo(ReturnCodeEnum.SUCCESS.getCode());
		Assertions.assertThat(result.getData().toString()).isEqualTo(xmlProductDto.toString());
	}
	
}
