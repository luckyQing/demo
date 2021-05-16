package com.liyulin.shiro.webflux.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionMapper {

    Set<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);

}