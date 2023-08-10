package com.liyulin.demo.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_test")
public class TestEntity {

    @TableId("f_id")
    private Long id;

    @TableField("f_name")
    private String name;

    @TableLogic
    @TableField("f_del")
    private Integer del;

}