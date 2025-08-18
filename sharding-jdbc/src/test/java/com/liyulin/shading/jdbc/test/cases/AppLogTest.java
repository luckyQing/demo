package com.liyulin.shading.jdbc.test.cases;

import com.liyulin.shading.jdbc.Application;
import com.liyulin.shading.jdbc.entity.ThirdLogEntity;
import com.liyulin.shading.jdbc.repository.ThirdLogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class AppLogTest {

    @Autowired
    private ThirdLogRepository thirdLogRepository;

    @Test
    public void testSave() {
        ThirdLogEntity thirdLogEntity = new ThirdLogEntity();
        thirdLogEntity.setVendor("izidata");
        thirdLogEntity.setUrl("https://test");
        thirdLogEntity.setReqStartTime(LocalDateTime.now());
        thirdLogEntity.setReqEndTime(LocalDateTime.now());
        thirdLogEntity.setCostTime(2);
        thirdLogEntity.setReqParams("test");
        thirdLogEntity.setResponse("success");
        LocalDateTime createTime = LocalDateTime.of(2023, 5, 1, 0, 0, 0);
        thirdLogEntity.setCreateTime(createTime);
        // 插入数据
        Assertions.assertThat(thirdLogRepository.save(thirdLogEntity)).isTrue();
    }

    @Test
    public void testBatchSave() {
        List<ThirdLogEntity> logEntityList = new ArrayList<>();
        for (int year = 2022; year <= 2025; year++) {
            ThirdLogEntity thirdLogEntity = new ThirdLogEntity();
            thirdLogEntity.setVendor("izidata");
            thirdLogEntity.setUrl("https://test");
            thirdLogEntity.setReqStartTime(LocalDateTime.now());
            thirdLogEntity.setReqEndTime(LocalDateTime.now());
            thirdLogEntity.setCostTime(2);
            thirdLogEntity.setReqParams("test");
            thirdLogEntity.setResponse("success");
            LocalDateTime createTime = LocalDateTime.of(year, 1, 1, 0, 0, 0);
            thirdLogEntity.setCreateTime(createTime);
            logEntityList.add(thirdLogEntity);
        }
        // 插入数据
        Assertions.assertThat(thirdLogRepository.saveBatch(logEntityList)).isTrue();
    }

}