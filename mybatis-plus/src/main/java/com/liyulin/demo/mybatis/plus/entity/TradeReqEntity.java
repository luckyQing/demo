package com.liyulin.demo.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Leo xu
 * @Desc 请求表, 表对应的实体对象
 * @date 2018年01月01日 09:09:09
 * @Copyright (c) youxin
 */
@Getter
@Setter
@TableName("trade_req")
public class TradeReqEntity {

    /**
     * 雪花id
     */
    @TableId("id")
    protected Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    protected Long userId;

    /**
     * 客户短号
     */
    @TableField("user_id_short")
    protected Long userIdShort;

    /**
     * 用户对应的恒生账户
     */
    @TableField("fund_account")
    protected String fundAccount;

    /**
     * 账号业务类型：100-正股，101-日内融账户，102-DA，103-MA公共，104-MA费用，105-跟投，300-期权账户，400-沽空，501-基金，502-现金加
     */
    @TableField("account_business_type")
    protected Integer accountBusinessType;

    /**
     * 业务系统当次业务请求幂等标识
     */
    @TableField("partner_trade_no")
    protected String partnerTradeNo;

    /**
     * 交易业务类型
     */
    @TableField("trade_type")
    protected Integer tradeType;

    /**
     * 交易业务子类型
     */
    @TableField("trade_sub_type")
    protected String tradeSubType;

    /**
     * 业务相关的id
     */
    @TableField("business_id")
    protected Long businessId;

    /**
     * 币种类型，CNY人民币，USD美元，HKD港币
     */
    @TableField("money_type")
    protected String moneyType;

    /**
     * 市场类型，HK港股，US美股，HGT沪港通，SGT深港通
     */
    @TableField("exchange_type")
    protected String exchangeType;

    /**
     * 交易操作状态10待处理  20处理中 21处理中,明细有失败 30成功 40失败 41失败待确认
     */
    @TableField("trade_status")
    protected Integer tradeStatus;

    /**
     * 该操作的交易日时间
     */
    @TableField("tran_date")
    protected LocalDate tranDate;

    /**
     * 该流水业务发生的时间
     */
    @TableField("business_time")
    protected Date businessTime;

    /**
     * 请求url
     */
    @TableField("req_url")
    protected String reqUrl;

    /**
     * 请求参数
     */
    @TableField("req_data")
    protected String reqData;

    /**
     * 响应数据
     */
    @TableField("resp_data")
    protected String respData;

    /**
     * 备注
     */
    @TableField("remark")
    protected String remark;

    /**
     * 乐观锁版本号
     */
    @TableField("version")
    protected Integer version;

    /**
     * 创建时间
     */
    @TableField("create_time")
    protected Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    protected Date updateTime;

    /**
     * 实体对象的属性字段
     */
    public enum Property {
        /**
         * 雪花id
         */
        id,
        /**
         * 用户ID
         */
        userId,
        /**
         * 客户短号
         */
        userIdShort,
        /**
         * 用户对应的恒生账户
         */
        fundAccount,
        /**
         * 账号业务类型：100-正股，101-日内融账户，102-DA，103-MA公共，104-MA费用，105-跟投，300-期权账户，400-沽空，501-基金，502-现金加
         */
        accountBusinessType,
        /**
         * 业务系统当次业务请求幂等标识
         */
        partnerTradeNo,
        /**
         * 交易业务类型
         */
        tradeType,
        /**
         * 交易业务子类型
         */
        tradeSubType,
        /**
         * 业务相关的id
         */
        businessId,
        /**
         * 币种类型，CNY人民币，USD美元，HKD港币
         */
        moneyType,
        /**
         * 市场类型，HK港股，US美股，HGT沪港通，SGT深港通
         */
        exchangeType,
        /**
         * 交易操作状态10待处理  20处理中 21处理中,明细有失败 30成功 40失败 41失败待确认
         */
        tradeStatus,
        /**
         * 该操作的交易日时间
         */
        tranDate,
        /**
         * 该流水业务发生的时间
         */
        businessTime,
        /**
         * 请求url
         */
        reqUrl,
        /**
         * 请求参数
         */
        reqData,
        /**
         * 响应数据
         */
        respData,
        /**
         * 备注
         */
        remark,
        /**
         * 乐观锁版本号
         */
        version,
        /**
         * 创建时间
         */
        createTime,
        /**
         * 更新时间
         */
        updateTime,
        ;
    }
}

