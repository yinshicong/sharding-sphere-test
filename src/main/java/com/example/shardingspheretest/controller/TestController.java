package com.example.shardingspheretest.controller;

import com.example.shardingspheretest.model.request.UserParam;
import com.example.shardingspheretest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yinsc
 * @Date: 2020/11/20
 * @Description:
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserService userService;

    @PostMapping("t")
    public String test(@RequestBody UserParam userParam) {
        userService.insert(userParam);
        return "hello world~";
    }

}
