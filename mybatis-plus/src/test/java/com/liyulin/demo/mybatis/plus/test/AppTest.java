package com.liyulin.demo.mybatis.plus.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liyulin.demo.mybatis.plus.biz.TestBiz;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private TestBiz testBiz;

    @Test
    public void testInsert() {
        long t1 = System.currentTimeMillis();
        for (int k = 0; k < 1000; k++) {
            List<TestEntity> data = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                TestEntity testEntity = new TestEntity();
                testEntity.setName("lss订单");
                testEntity.setSysInsertTime(LocalDateTime.now());
                testEntity.setSysUpdTime(LocalDateTime.now());
                testEntity.setSysInsertUser(123L);
                testEntity.setSysDelState((byte) 1);
                data.add(testEntity);
            }
            testBiz.saveBatch(data);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    @Test
    public void testSelect() {
        List<String> names = new ArrayList<>();
        LambdaQueryWrapper<TestEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!names.isEmpty(), TestEntity::getName, names);
        testBiz.list(queryWrapper);
    }

    @Test
    public void testLogicDelete() {
        testBiz.removeById(1689463406473592834L);
    }

    @Test
    public void testSelectLogicDeleted() {
        LambdaQueryWrapper<TestEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TestEntity::getSysDelState, 1);
        testBiz.list(queryWrapper);
    }

    @Test
    public void testStreamQuery() {
        List<TestEntity> testEntities = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setId(i + 1L);
            testEntity.setName("lss订单" + i);
            testEntity.setSysInsertTime(LocalDateTime.now());
            testEntities.add(testEntity);
        }
        testBiz.saveBatch(testEntities);

        testBiz.streamQuery(context -> {
            Long id = context.getResultObject();
            System.out.println(id);
        });
    }

    @Test
    public void testStreamQueryList() {
        List<TestEntity> testEntities = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setId(i + 1L);
            testEntity.setName("lss订单" + i);
            testEntity.setSysInsertTime(LocalDateTime.now());
            testEntities.add(testEntity);
        }
        testBiz.saveBatch(testEntities);

        testBiz.streamQueryList(context -> {
            Long id = context.getResultObject();
            System.out.println(id);
        });
    }

    @Test
    public void testStreamQueryWithParams() {
        List<TestEntity> testEntities = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setId(i + 1L);
            testEntity.setName("lss订单" + i);
            testEntity.setSysInsertTime(LocalDateTime.now());
            testEntities.add(testEntity);
        }
        testBiz.saveBatch(testEntities);

        testBiz.streamQueryWithParams(1L, 50L, context -> {
            Long id = context.getResultObject();
            System.out.println(id);
        });
    }

}