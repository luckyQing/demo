package com.liyulin.demo.tkmybatis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "t_test")
public class TestEntity {

    @Id
    @Column(name = "f_id")
    private Long id;

    @Column(name = "f_name")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "f_sys_insert_time")
    private LocalDateTime sysInsertTime;

    /**
     * 更新时间
     */
    @Column(name = "f_sys_upd_time")
    private LocalDateTime sysUpdTime;

    /**
     * 删除时间
     */
    @Column(name = "f_sys_del_time")
    private LocalDateTime sysDelTime;

    /**
     * 新增者
     */
    @Column(name = "f_sys_insert_user")
    private Long sysInsertUser;

    /**
     * 更新者
     */
    @Column(name = "f_sys_upd_user")
    private Long sysUpdUser;

    /**
     * 删除者
     */
    @Column(name = "f_sys_del_user")
    private Long sysDelUser;

    /**
     * 删除状态=={1:正常, 2:已删除}
     */
    @Column(name = "f_sys_del_state")
    private Byte sysDelState;

}