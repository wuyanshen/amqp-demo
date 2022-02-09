package com.lvcoding.amqpdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description 描述
 * @date   2022-02-08 下午4:04
 * @author  wuyanshen
 */
@RestController
public class TestController {

    @Autowired
    private Environment environment;

    @GetMapping("env")
    public String getEnv(String env) {
       return environment.getProperty(env);
    }
}
