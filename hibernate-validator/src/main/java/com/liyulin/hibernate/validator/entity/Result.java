package com.liyulin.hibernate.validator.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * 返回数据封装
 *
 * @author liyulin
 * @version 1.0 2018年3月31日 下午3:33:56
 */
@Data
public class Result implements Serializable {
	private static final long serialVersionUID = -4515813899894007543L;

	private String msg;
	private String code;
	private JSONObject data;
	private String transNo;

	public Result() {
		super();
		this.msg = StateCode.OK.getMsg();
		this.code = StateCode.OK.getCode();
		this.data = new JSONObject();
	}

	public Result(JSONObject data) {
		this();
		this.data = data;
	}

	public Result setSystemError() {
		this.msg = StateCode.SYSTEM_ERROR.getMsg();
		this.code = StateCode.SYSTEM_ERROR.getCode();
		return this;
	}

	public Result setValidateError() {
		this.msg = StateCode.VALIDATE_ERROR.getMsg();
		this.code = StateCode.VALIDATE_ERROR.getCode();
		return this;
	}

	public Result setBusinessFail() {
		this.msg = StateCode.BUSINESS_FAILED.getMsg();
		this.code = StateCode.BUSINESS_FAILED.getCode();
		return this;
	}

	public Result setOtherError() {
		this.msg = StateCode.OTHER_ERROR.getMsg();
		this.code = StateCode.OTHER_ERROR.getCode();
		return this;
	}

	public Result put(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public Result(String msg, String code) {
		this();
		this.msg = msg;
		this.code = code;
	}

}
