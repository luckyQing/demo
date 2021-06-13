package com.liyulin.security.webflux.controller;

import com.liyulin.security.webflux.pojo.vo.RespVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * test
 *
 * @author collin
 * @date 2021-06-15
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("vip")
    @PreAuthorize("hasAnyRole('vip')")
    public Mono<RespVO<String>> vip() {
        return Mono.just(RespVO.success("vip success"));
    }

    @GetMapping("admin")
    @PreAuthorize("hasAnyRole('admin')")
    public Mono<RespVO<String>> admin() {
        return Mono.just(RespVO.success("admin success"));
    }

    @PreAuthorize("hasAnyAuthority('add')")
    @GetMapping("add")
    public Mono<RespVO<String>> add() {
        return Mono.just(RespVO.success("add success"));
    }

    @PreAuthorize("hasAnyAuthority('update')")
    @GetMapping("update")
    public Mono<RespVO<String>> update() {
        return Mono.just(RespVO.success("update success"));
    }

    @PreAuthorize("hasAnyAuthority('select')")
    @GetMapping("select")
    public Mono<RespVO<String>> select() {
        return Mono.just(RespVO.success("select success"));
    }

}