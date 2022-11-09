package com.liyulin.demo.retrofit.test;

import com.liyulin.demo.retrofit.test.api.ITestClient;
import io.github.smart.cloud.common.pojo.Response;
import io.github.smart.cloud.constants.CommonReturnCodes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RetrofitTest {

    @Test
    void test() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:80/").addConverterFactory(JacksonConverterFactory.create()).build();

        ITestClient testClient = retrofit.create(ITestClient.class);
        Call<Response<String>> result = testClient.test("collin");
        Response<String> response = result.execute().body();
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getHead()).isNotNull();
        Assertions.assertThat(response.getHead().getCode()).isEqualTo(CommonReturnCodes.SUCCESS);
        Assertions.assertThat(response.getBody()).isNotNull();
    }

}