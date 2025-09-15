package com.liyulin.demo.tkmybatis.dao;

import com.liyulin.demo.tkmybatis.entity.TestEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author collin
 * @since 2025-01-19
 */
public interface TestDao extends Mapper<TestEntity>, InsertListMapper<TestEntity> {

}