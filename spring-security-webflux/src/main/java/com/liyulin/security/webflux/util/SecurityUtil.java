package com.liyulin.security.webflux.util;

import com.liyulin.security.webflux.enums.ReturnCode;
import com.liyulin.security.webflux.pojo.vo.RespVO;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * security工具类
 *
 * @author collin
 * @date 2021-06-17
 */
public final class SecurityUtil {

    public static Mono<Void> withErrorResponse(ReturnCode code, ServerHttpResponse response) {
        return withSuccessResponse(new RespVO(code), response);
    }

    public static <T> Mono<Void> withSuccessResponse(T data, ServerHttpResponse response) {
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(JacksonUtil.toBytes(data));
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    /**
     * 生成token
     *
     * @return
     */
    public static String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}