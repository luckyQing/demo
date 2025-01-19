package com.liyulin.demo.mybatis.plus.dao;

import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author collin
 * @since 2025-01-19
 */
@Mapper
public interface TestDao extends BaseMapper<TestEntity> {

    /**
     * 流式查询
     *
     * @return
     */
    void streamQuery(ResultHandler<Long> resultHandler);

    @Select("select f_id from t_test")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 500)
    @ResultType(Long.class)
    void streamQueryList(ResultHandler<Long> resultHandler);

    /**
     * 流式查询（带参数）
     *
     * @return
     */
    void streamQueryWithParams(@Param("minId") Long minId, @Param("maxId") Long maxId, ResultHandler<Long> resultHandler);

}