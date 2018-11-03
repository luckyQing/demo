package com.liyulin.http.message.converter.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态
 *
 * @author liyulin
 * @date 2018年11月3日上午8:28:29
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReturnCodeEnum {
	
	/** 成功 */
	SUCCESS(200, "成功"),
	/** 失败 */
	FAIL(500, "失败");
	private int code;
	private String msg;
	
}
