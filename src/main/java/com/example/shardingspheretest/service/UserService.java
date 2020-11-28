package com.example.shardingspheretest.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.shardingspheretest.dal.dao.UserMapper;
import com.example.shardingspheretest.model.po.User;
import com.example.shardingspheretest.model.request.UserParam;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yinsc
 * @Date: 2020/11/20
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        userMapper.insertSelective(user);
    }

}
