package com.liyulin.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulin.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}