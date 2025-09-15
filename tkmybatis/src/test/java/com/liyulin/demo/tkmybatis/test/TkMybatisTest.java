package com.liyulin.demo.tkmybatis.test;

import com.liyulin.demo.tkmybatis.dao.TestDao;
import com.liyulin.demo.tkmybatis.entity.TestEntity;
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
public class TkMybatisTest {

    @Autowired
    private TestDao testDao;

    @Test
    public void testBatchInsert() {
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
            testDao.insertList(data);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("insertList:%sms", t2 - t1));
    }

    @Test
    public void testAddBatch() {
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
            testDao.addBatch(data);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("addBatch:%sms", t2 - t1));
    }

}