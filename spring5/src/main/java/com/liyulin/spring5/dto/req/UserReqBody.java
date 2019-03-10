package com.liyulin.spring5.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReqBody {

	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;
	/** 地址 */
	private String address;

}