package com.liyulin.demo.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import com.liyulin.demo.mybatis.plus.mapper.TestMapper;
import com.liyulin.demo.mybatis.plus.service.ITestService;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements ITestService {

    @Override
    public void streamQuery(ResultHandler<Long> resultHandler) {
        baseMapper.streamQuery(resultHandler);
    }

    @Override
    public void streamQueryWithParams(Long minId, Long maxId, ResultHandler<Long> resultHandler) {
        baseMapper.streamQueryWithParams(minId, maxId, resultHandler);
    }

}