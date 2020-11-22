package com.example.shardingspheretest.model.request;

import com.example.shardingspheretest.model.po.User;
import lombok.Data;

import java.util.Date;

/**
 * @author: yinsc
 * @Date: 2020/11/20
 * @Description:
 */
@Data
public class UserParam {

    private Integer id;

    private String name;

    private Integer gender;

    private Integer age;

    private Integer nationality;

    private Integer areacode;

    private Date bornDate;

    private String birthday;

    private Date createDate;

    private String createUser;

}
