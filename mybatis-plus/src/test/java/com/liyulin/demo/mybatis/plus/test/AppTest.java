package com.liyulin.demo.mybatis.plus.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import com.liyulin.demo.mybatis.plus.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private ITestService testService;

    @Test
    public void shouldAnswerWithTrue() {
        LambdaQueryWrapper<TestEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TestEntity::getName, new ArrayList<>());
        testService.list(queryWrapper);
    }

}