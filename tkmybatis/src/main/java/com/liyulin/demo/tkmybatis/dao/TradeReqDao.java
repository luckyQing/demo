package com.liyulin.demo.tkmybatis.dao;

import com.liyulin.demo.tkmybatis.entity.TradeReqEntity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface TradeReqDao extends Mapper<TradeReqEntity>, InsertListMapper<TradeReqEntity> {

    Integer addBatch(List<TradeReqEntity> list);

}