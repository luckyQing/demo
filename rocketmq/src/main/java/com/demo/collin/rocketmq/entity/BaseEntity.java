package com.demo.collin.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * entity基类（表公共字段）
 * </p>
 *
 * @author collin
 * @since 2025-01-15
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("f_id")
    private Long id;

    /**
     * 创建时间
     */
    @TableField("f_sys_insert_time")
    private LocalDateTime sysInsertTime;

    /**
     * 更新时间
     */
    @TableField("f_sys_upd_time")
    private LocalDateTime sysUpdTime;

    /**
     * 删除时间
     */
    @TableField("f_sys_del_time")
    private LocalDateTime sysDelTime;

    /**
     * 新增者
     */
    @TableField("f_sys_insert_user")
    private Long sysInsertUser;

    /**
     * 更新者
     */
    @TableField("f_sys_upd_user")
    private Long sysUpdUser;

    /**
     * 删除者
     */
    @TableField("f_sys_del_user")
    private Long sysDelUser;

    /**
     * 删除状态=={1:正常, 2:已删除}
     */
    @TableField("f_sys_del_state")
    @TableLogic
    private Byte sysDelState;

}