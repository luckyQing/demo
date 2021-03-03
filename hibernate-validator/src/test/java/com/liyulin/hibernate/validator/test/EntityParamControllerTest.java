package com.liyulin.hibernate.validator.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liyulin.hibernate.validator.entity.QryTransResultReqVO;
import com.liyulin.hibernate.validator.entity.Result;
import com.liyulin.hibernate.validator.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

/**
 * @author collin
 * @date 2021-03-03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EntityParamControllerTest {

    protected MockMvc mockMvc = null;
    @Autowired
    protected WebApplicationContext applicationContext;

    @Before
    public void initMock() {
        if (this.mockMvc == null) {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
        }
    }

    @Test
    public void testGetBody() throws Exception {
        QryTransResultReqVO reqVO = new QryTransResultReqVO();
        reqVO.setTransactions(Arrays.asList(new QryTransResultReqVO.Transaction(1L, 2L)));
        Result result = getByBody("/test", reqVO, new TypeReference<Result>(){});
    }

    public <T> T getByBody(String url, Object req, TypeReference<T> typeReference) throws Exception {
        String requestJsonStr = JacksonUtil.toJson(req);
        log.info("test.requestBody={}", requestJsonStr);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(url, new Object[0]).content(requestJsonStr)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType("application/json")
                .accept(new String[]{"application/json"});

        MockHttpServletResponse response = this.mockMvc.perform(mockHttpServletRequestBuilder).andReturn().getResponse();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String content = response.getContentAsString();
        log.info("test.result={}", content);
        return JacksonUtil.parseObject(content, typeReference);
    }

}