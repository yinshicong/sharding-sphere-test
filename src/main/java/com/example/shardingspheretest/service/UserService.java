package com.example.shardingspheretest.service;

import com.example.shardingspheretest.dal.dao.UserMapper;
import com.example.shardingspheretest.dal.dao.UserMapperCustomer;
import com.example.shardingspheretest.model.po.User;
import com.example.shardingspheretest.model.request.ListUserParam;
import com.example.shardingspheretest.model.request.UserParam;
import com.example.shardingspheretest.model.vo.ListUserVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: yinsc
 * @Date: 2020/11/20
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMapperCustomer userMapperCustomer;

    public void createUser(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        userMapper.insertSelective(user);
    }

    public List<ListUserVO> list(ListUserParam listUserParam) {
        List<User> list = userMapperCustomer.listPage(listUserParam);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<ListUserVO> result = new ArrayList<>();
        for (User user : list) {
            ListUserVO listUserVO = new ListUserVO();
            BeanUtils.copyProperties(user,listUserVO);
            result.add(listUserVO);
        }
        return result;
    }
}
