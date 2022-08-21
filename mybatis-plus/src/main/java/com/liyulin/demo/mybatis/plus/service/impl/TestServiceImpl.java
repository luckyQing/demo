package com.liyulin.demo.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import com.liyulin.demo.mybatis.plus.mapper.TestMapper;
import com.liyulin.demo.mybatis.plus.service.ITestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements ITestService {
}