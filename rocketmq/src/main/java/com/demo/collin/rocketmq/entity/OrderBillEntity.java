package com.demo.collin.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.collin.rocketmq.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单信息
 * </p>
 *
 * @author collin
 * @since 2025-01-15
 */
@Getter
@Setter
@TableName("t_order_bill")
public class OrderBillEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableField("f_order_no")
    private String orderNo;

    /**
     * 订单金额总金额
     */
    @TableField("f_amount")
    private Long amount;

    /**
     * 订单状态（1：待扣减库存；2：扣减库存失败；3：抵扣优惠券失败；4：待付款；5：已取消；6：待发货；7：待收货；8：待评价，9：已完成）
     */
    @TableField("f_status")
    private Byte status;

    /**
     * 支付状态（1：待支付；2：支付成功；3：支付失败；4：待退款；5：退款成功；6：退款失败）
     */
    @TableField("f_pay_state")
    private Byte payState;

    /**
     * 购买人id（demo_user库t_user_info表f_id）
     */
    @TableField("f_buyer")
    private Long buyer;
}
