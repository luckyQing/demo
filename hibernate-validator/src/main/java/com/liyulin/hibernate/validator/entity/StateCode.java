package com.liyulin.hibernate.validator.entity;

import lombok.Getter;

/**
 * 状态码
 * 
 * <p>
 * OK：成功<br>
 * SYSTEM_ERROR：系统异常<br>
 * VALIDATE_ERROR：校验异常<br>
 * COMMUNICATE_ERROR：通讯异常<br>
 * BUSINESS_FAILED：业务处理异常<br>
 * OTHER_ERROR：其它错误
 *
 * @author liyulin
 * @version 1.0 2018年3月31日 下午3:05:07
 */
@Getter
public enum StateCode {

	/** 成功 */
	OK("200", "操作成功"),
	/** 系统异常 */
	SYSTEM_ERROR("500", "系统异常"),
	/** 校验异常 */
	VALIDATE_ERROR("400", "校验异常"),
	/** 通讯异常 */
	COMMUNICATE_ERROR("408", "通讯异常"),
	/** 业务处理异常 */
	BUSINESS_FAILED("501", "业务处理异常"),
	/** 其它错误 */
	OTHER_ERROR("999", "其他错误");

	private String code;
	private String msg;

	private StateCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
