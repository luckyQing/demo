package com.liyulin.demo.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

public interface ITestService extends IService<TestEntity> {

    /**
     * 流式查询
     *
     * @return
     */
    void streamQuery(ResultHandler<Long> resultHandler);

    /**
     * 流式查询
     *
     * @return
     */
    void streamQueryList(ResultHandler<Long> resultHandler);

    /**
     * 流式查询（带参数）
     *
     * @return
     */
    void streamQueryWithParams(Long minId, Long maxId, ResultHandler<Long> resultHandler);

}