package com.liyulin.demo.retrofit.test.api;

import io.github.smart.cloud.common.pojo.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITestClient {

    @GET("test")
    Call<Response<String>> test(@Query("name") String name);

}