package com.liyulin.http.message.converter.unit.cases;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.http.message.converter.enums.ReturnCodeEnum;
import com.liyulin.http.message.converter.json.dto.JsonProductDto;
import com.liyulin.http.message.converter.json.dto.JsonResult;
import com.liyulin.http.message.converter.unit.base.BaseUnitTest;

public class JsonProductControllerTest extends BaseUnitTest {

	@Test
	public void testJson() throws Exception {
		JsonProductDto jsonProductDto = new JsonProductDto();
		jsonProductDto.setId(1000L);
		jsonProductDto.setName("手机");
		jsonProductDto.setPrice(288800);

		JsonResult<JsonProductDto> result = super.postJson("/json", jsonProductDto, new TypeReference<JsonResult<JsonProductDto>>() {});

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getCode()).isEqualTo(ReturnCodeEnum.SUCCESS.getCode());
		Assertions.assertThat(result.getData().toString()).isEqualTo(jsonProductDto.toString());
	}

}
