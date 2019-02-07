package com.liyulin.webservice.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRespBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private long price;
	private int buyCount;

}