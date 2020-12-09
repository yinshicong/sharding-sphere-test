package com.example.shardingspheretest.dal.dao;

import com.example.shardingspheretest.model.po.User;
import com.example.shardingspheretest.model.po.UserExample;
import com.example.shardingspheretest.model.request.ListUserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapperCustomer {

    List<User> listPage(ListUserParam listUserParam);

}