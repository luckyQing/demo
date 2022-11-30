package com.liyulin.demo.logback.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
}