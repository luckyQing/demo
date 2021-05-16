package com.liyulin.shiro.webflux.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liyulin.shiro.webflux.entity.User;
import com.liyulin.shiro.webflux.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author collin
 * @date 2021-05-24
 */
@Component
public class UserBiz {

    @Autowired
    private UserMapper userMapper;

    public User findByAccount(String account) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount, account));
    }

}