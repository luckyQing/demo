package com.liyulin.webservice.cfx.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRespBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String email;

}