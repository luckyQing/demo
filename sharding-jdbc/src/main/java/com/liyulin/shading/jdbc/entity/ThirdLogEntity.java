package com.liyulin.shading.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@TableName("t_thrid_log")
public class ThirdLogEntity {

    private static final long serialVersionUID = 1L;

    @TableId("f_id")
    private Long id;
    /**
     * 供用商
     */
    @TableField("f_vendor")
    private String vendor;

    /**
     * 接口url
     */
    @TableField("f_url")
    private String url;

    /**
     * 异常堆栈信息
     */
    @TableField("f_exception_msg")
    private String exceptionMsg;

    /**
     * 请求开始时间
     */
    @TableField("f_req_start_time")
    private LocalDateTime reqStartTime;

    /**
     * 请求截止时间
     */
    @TableField("f_req_end_time")
    private LocalDateTime reqEndTime;

    /**
     * 请求耗时（毫秒）
     */
    @TableField("f_cost_time")
    private Integer costTime;

    /**
     * 请求的参数信息
     */
    @TableField("f_req_params")
    private String reqParams;

    /**
     * 响应的数据
     */
    @TableField("f_response")
    private String response;

    /**
     * 创建时间
     */
    @TableField("f_create_time")
    private LocalDateTime createTime;

}