package com.liyulin.demo.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import org.apache.ibatis.session.ResultHandler;

public interface ITestService extends IService<TestEntity> {

    /**
     * 流式查询
     *
     * @return
     */
    void streamQuery(ResultHandler<Long> resultHandler);

}