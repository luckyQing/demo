package com.liyulin.demo.mybatis.plus.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulin.demo.mybatis.plus.dao.TestDao;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author collin
 * @since 2025-01-19
 */
@Service
public class TestBiz extends ServiceImpl<TestDao, TestEntity> {

    /**
     * 流式查询
     *
     * @return
     */
    public void streamQuery(ResultHandler<Long> resultHandler) {
        baseMapper.streamQuery(resultHandler);
    }

    public void streamQueryList(ResultHandler<Long> resultHandler) {
        baseMapper.streamQueryList(resultHandler);
    }

    /**
     * 流式查询（带参数）
     *
     * @return
     */
    public void streamQueryWithParams(Long minId, Long maxId, ResultHandler<Long> resultHandler) {
        baseMapper.streamQueryWithParams(minId, maxId, resultHandler);
    }

}