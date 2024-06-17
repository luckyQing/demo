package com.liyulin.demo.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

@Mapper
public interface TestMapper extends BaseMapper<TestEntity> {

    /**
     * 流式查询
     *
     * @return
     */
    void streamQuery(ResultHandler<Long> resultHandler);

    /**
     * 流式查询（带参数）
     *
     * @return
     */
    void streamQueryWithParams(@Param("minId") Long minId, @Param("maxId") Long maxId, ResultHandler<Long> resultHandler);

}