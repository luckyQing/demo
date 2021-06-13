package com.liyulin.security.webflux.pojo.vo;

import com.liyulin.security.webflux.enums.ReturnCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RespVO<T> implements Serializable {

    private static final long serialVersionUID = -1005863670741860901L;
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 响应内容
     */
    private T data;

    public RespVO(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.message = returnCode.getMsg();
    }

    public RespVO(ReturnCode returnCode, T data) {
        this(returnCode);
        this.data = data;
    }

    public RespVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回成功信息
     *
     * @param data 信息内容
     * @param <T>
     * @return
     */
    public static <T> RespVO success(T data) {
        return new RespVO<>(ReturnCode.SUCCESS, data);
    }

    /**
     * 返回成功信息
     *
     * @return
     */
    public static RespVO success() {
        return new RespVO(ReturnCode.SUCCESS);
    }

    /**
     * 返回错误信息
     *
     * @param returnCode 响应码
     * @return
     */
    public static RespVO error(ReturnCode returnCode) {
        return new RespVO(returnCode);
    }

}