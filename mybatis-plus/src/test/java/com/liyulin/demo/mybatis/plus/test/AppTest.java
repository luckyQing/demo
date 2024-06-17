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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private ITestService testService;

    @Test
    public void testInsert() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("lss订单");
        testService.save(testEntity);
    }

    @Test
    public void testSelect() {
        List<String> names = new ArrayList<>();
        LambdaQueryWrapper<TestEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!names.isEmpty(), TestEntity::getName, names);
        testService.list(queryWrapper);
    }

    @Test
    public void testLogicDelete() {
        testService.removeById(1689463406473592834L);
    }

    @Test
    public void testSelectLogicDeleted() {
        LambdaQueryWrapper<TestEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TestEntity::getDel, 1);
        testService.list(queryWrapper);
    }

    @Test
    public void testStreamQuery() {
        List<TestEntity> testEntities = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setName("lss订单" + i);
            testEntities.add(testEntity);
        }
        testService.saveBatch(testEntities);

        testService.streamQuery(context -> {
            Long id = context.getResultObject();
            System.out.println(id);
        });
    }

}