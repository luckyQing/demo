package com.liyulin.shading.jdbc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.liyulin.shading.jdbc.base.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "t_api_log")
public class ApiLogEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 接口描述 */
	@Column(name = "f_api_desc")
	private String apiDesc;

	/** 调用的类方法 */
	@Column(name = "f_class_method")
	private String classMethod;

	/** 接口url */
	@Column(name = "f_url")
	private String url;

	/** http请求方式 */
	@Column(name = "f_http_method")
	private String httpMethod;

	/** 客户端ip */
	@Column(name = "f_ip")
	private String ip;

	/** 操作系统相关信息 */
	@Column(name = "f_os")
	private String os;

	/** 异常堆栈信息 */
	@Column(name = "f_exception_stack_info")
	private String exceptionStackInfo;

	/** 请求开始时间 */
	@Column(name = "f_req_start_time")
	private Date reqStartTime;

	/** 请求截止时间 */
	@Column(name = "f_req_end_time")
	private Date reqEndTime;

	/** 请求处理时间（毫秒） */
	@Column(name = "f_req_deal_time")
	private Integer reqDealTime;

	/** 请求的参数信息 */
	@Column(name = "f_req_params")
	private String reqParams;

	/** 响应的数据 */
	@Column(name = "f_resp_data")
	private String respData;

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum Columns {
		/** 接口描述 */
		API_DESC("apiDesc", "f_api_desc"),
		/** 调用的类方法 */
		CLASS_METHOD("classMethod", "f_class_method"),
		/** 接口url */
		URL("url", "f_url"),
		/** http请求方式 */
		HTTP_METHOD("httpMethod", "f_http_method"),
		/** 客户端ip */
		IP("ip", "f_ip"),
		/** 操作系统相关信息 */
		OS("os", "f_os"),
		/** 异常堆栈信息 */
		EXCEPTION_STACK_INFO("exceptionStackInfo", "f_exception_stack_info"),
		/** 请求开始时间 */
		REQ_START_TIME("reqStartTime", "f_req_start_time"),
		/** 请求截止时间 */
		REQ_END_TIME("reqEndTime", "f_req_end_time"),
		/** 请求处理时间（毫秒） */
		REQ_DEAL_TEIM("reqDealTime", "f_req_deal_time"),
		/** 请求的参数信息 */
		REQ_PARAMS("reqParams", "f_req_params"),
		/** 响应的数据 */
		RESP_DATA("respData", "f_resp_data");

		private String property;
		private String column;
	}

}