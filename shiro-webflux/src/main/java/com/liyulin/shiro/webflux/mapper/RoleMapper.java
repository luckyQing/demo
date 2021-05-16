package com.liyulin.shiro.webflux.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulin.shiro.webflux.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRoleByUserId(@Param("userId") Integer userId);

}