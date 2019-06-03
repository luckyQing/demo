package com.liyulin.shading.jdbc.demo.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表字段f_sys_del_state对应的枚举值
 *
 * @author liyulin
 * @date 2019年3月24日下午4:54:31
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DelStateEnum {

	/** 正常 */
	NORMAL(1),
	/** 已删除 */
	DELETED(2);

	/** 记录状态 */
	private Integer delState;

}