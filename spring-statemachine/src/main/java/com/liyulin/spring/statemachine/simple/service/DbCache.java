package com.liyulin.spring.statemachine.simple.service;


import com.liyulin.spring.statemachine.simple.vo.OrderVO;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author collin
 * @date 2020-08-14
 */
public class DbCache {

    private static final ConcurrentMap<Integer, OrderVO> CACHE = new ConcurrentHashMap<>();

    public static void add(OrderVO vo){
        CACHE.put(vo.getId(), vo);
    }

    public static OrderVO getOrderVO(Integer id){
        return CACHE.get(id);
    }

}
