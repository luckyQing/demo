package com.liyulin.shiro.webflux.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulin.shiro.webflux.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}