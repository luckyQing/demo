package com.liyulin.shiro.webflux.controller;

import com.liyulin.shiro.webflux.enums.ServerResponseEnum;
import com.liyulin.shiro.webflux.vo.ServerResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@Validated
public class LoginController {

    @PostMapping("/login")
    public ServerResponseVO<String> login(@RequestParam(value = "account") String account,
                                          @RequestParam(value = "password") String password) {
        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            // 登录验证
            userSubject.login(token);
            return ServerResponseVO.success(UUID.randomUUID().toString().replaceAll("-", ""));
        } catch (UnknownAccountException e) {
            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_NOT_EXIST);
        } catch (DisabledAccountException e) {
            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_IS_DISABLED);
        } catch (IncorrectCredentialsException e) {
            return ServerResponseVO.error(ServerResponseEnum.INCORRECT_CREDENTIALS);
        } catch (Throwable e) {
            log.error("login.error|account{}", account, e);
            return ServerResponseVO.error(ServerResponseEnum.ERROR);
        }
    }

    @GetMapping("/login")
    public ServerResponseVO login() {
        return ServerResponseVO.error(ServerResponseEnum.NOT_LOGIN_IN);
    }

    @GetMapping("/auth")
    public String auth() {
        return "已成功登录";
    }

    @GetMapping("/role")
    @RequiresRoles("vip")
    public String role() {
        return "测试Vip角色";
    }

    @GetMapping("/permission")
    @RequiresPermissions(value = {"add", "update"}, logical = Logical.AND)
    public String permission() {
        return "测试Add和Update权限";
    }

}