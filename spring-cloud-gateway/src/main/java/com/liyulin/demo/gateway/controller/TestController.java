package com.liyulin.demo.gateway.controller;

import com.liyulin.demo.gateway.dto.BodyDTO;
import com.liyulin.demo.gateway.dto.FormDTO;
import com.liyulin.demo.gateway.dto.GetDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping("get")
    public ResponseEntity<GetDTO> get(GetDTO dto) {
        log.info("getDTO={}", dto);
        dto.setHeight(dto.getHeight() + 1);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("getBody")
    public ResponseEntity<GetDTO> getBody(@RequestBody GetDTO dto) {
        log.info("getDTO={}", dto);
        dto.setHeight(dto.getHeight() + 2);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("form")
    public ResponseEntity<FormDTO> form(FormDTO dto) {
        log.info("formDTO={}", dto);
        dto.setPrice(dto.getPrice() + 3);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("body")
    public ResponseEntity<BodyDTO> body(@RequestBody BodyDTO dto, String x) {
        log.info("bodyDTO={}, x={}", dto, x);
        dto.setLength(dto.getLength() + 4);
        return ResponseEntity.ok(dto);
    }

}