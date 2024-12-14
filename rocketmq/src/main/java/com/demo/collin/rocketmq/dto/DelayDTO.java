package com.demo.collin.rocketmq.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DelayDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

}
