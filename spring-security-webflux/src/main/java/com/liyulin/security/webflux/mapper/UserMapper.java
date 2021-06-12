package com.liyulin.security.webflux.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulin.security.webflux.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}