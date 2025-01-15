package com.demo.collin.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.collin.rocketmq.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author collin
 * @since 2025-01-15
 */
@Getter
@Setter
@TableName("t_mq_transaction_log")
public class MqTransactionLogEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 事务号
     */
    @TableField("f_transaction_no")
    private String transactionNo;

    /**
     * 编码
     */
    @TableField("f_code")
    private String code;

    /**
     * 日志
     */
    @TableField("f_log")
    private String log;
}
