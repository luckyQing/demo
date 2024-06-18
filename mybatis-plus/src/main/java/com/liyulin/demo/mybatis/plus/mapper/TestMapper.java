package com.liyulin.demo.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<TestEntity> {

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