package com.liyulin.rabbitmq.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品信息dto
 *
 * @author liyulin
 * @date 2018年11月6日下午3:14:00
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class ProductDto implements Serializable {

    private long id;
    private String name;
    private long price;

}
