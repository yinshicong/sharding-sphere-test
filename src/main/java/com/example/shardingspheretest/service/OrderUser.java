package com.example.shardingspheretest.service;

import com.example.shardingspheretest.dal.dao.OrderMapper;
import com.example.shardingspheretest.model.po.Order;
import com.example.shardingspheretest.model.request.OrderParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yinsc
 * @Date: 2020/11/28
 * @Description:
 */
@Service
public class OrderUser {

    @Autowired
    private OrderMapper orderMapper;

    public void createOrder(OrderParam orderParam) {
        Order order = new Order();
        BeanUtils.copyProperties(orderParam, order);
        orderMapper.insertSelective(order);
    }

}
