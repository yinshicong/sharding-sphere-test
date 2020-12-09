package com.example.shardingspheretest.controller;

import com.example.shardingspheretest.model.request.ListUserParam;
import com.example.shardingspheretest.model.request.OrderParam;
import com.example.shardingspheretest.model.request.UserParam;
import com.example.shardingspheretest.model.vo.ListUserVO;
import com.example.shardingspheretest.service.OrderUser;
import com.example.shardingspheretest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yinsc
 * @Date: 2020/11/20
 * @Description:
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("list")
    public List<ListUserVO> list(@RequestBody ListUserParam listUserParam) {
        List<ListUserVO> list =  userService.list(listUserParam);
        return list;
    }

//    @PostMapping("createOrder")
//    public String createOrder(@RequestBody OrderParam orderParam) {
//        userService.createUser(orderParam);
//        return "createOrder success~";
//    }


}
