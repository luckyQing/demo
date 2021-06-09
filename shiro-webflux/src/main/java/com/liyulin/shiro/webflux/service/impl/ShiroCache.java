package com.liyulin.shiro.webflux.service.impl;

import com.liyulin.shiro.webflux.bo.ShiroMeta;

import java.util.HashMap;
import java.util.Map;

/**
 * @author collin
 * @date 2021-06-09
 */
public final class ShiroCache {

    private static final Map<String, ShiroMeta> SHIRO_META_CACHE = new HashMap<>();

    public static Map<String, ShiroMeta> getShiroMetaCache() {
        return SHIRO_META_CACHE;
    }

}