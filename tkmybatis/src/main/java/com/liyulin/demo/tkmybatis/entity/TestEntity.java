package com.liyulin.demo.tkmybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;

@ToString(callSuper = true)
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_test")
public class TestEntity extends BaseEntity {

    @Column(name = "f_name")
    private String name;

}