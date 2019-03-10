package com.liyulin.spring5.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRespBody {

	/** 主键id */
	private long id;
	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;
	/** 地址 */
	private String address;

}