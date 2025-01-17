package com.demo.collin.rocketmq.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyDTO extends BaseTransactionMsgDTO {

    /**
     * 订单金额总金额
     */
    private Long amount;
    /**
     * 购买人id（demo_user库t_user_info表f_id）
     */
    private Long buyer;

    /**
     * sku id
     */
    private Long skuId;

    /**
     * sku数量
     */
    private Long skuCount;

}