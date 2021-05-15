package com.liyulin.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulin.shiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRoleByUserId(@Param("userId") Integer userId);

}