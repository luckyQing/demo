package com.liyulin.shading.jdbc.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulin.shading.jdbc.entity.ThirdLogEntity;
import com.liyulin.shading.jdbc.mapper.ThirdLogMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ThirdLogRepository extends ServiceImpl<ThirdLogMapper, ThirdLogEntity> {
}