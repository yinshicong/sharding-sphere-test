package com.example.shardingspheretest.controller;

import com.example.shardingspheretest.model.request.OrderParam;
import com.example.shardingspheretest.model.request.UserParam;
import com.example.shardingspheretest.service.OrderUser;
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
@RequestMapping("api")
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderUser orderUser;

    @PostMapping("createUser")
    public String createUser(@RequestBody UserParam userParam) {
        userService.createUser(userParam);
        return "createUser success~";
    }

    @PostMapping("createOrder")
    public String createOrder(@RequestBody OrderParam orderParam) {
        orderUser.createOrder(orderParam);
        return "createOrder success~";
    }

}
