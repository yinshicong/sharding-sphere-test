package com.example.shardingspheretest.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: yinsc
 * @Date: 2020/12/9
 * @Description:
 */
@Data
public class ListUserVO {

    private Long id;

    private String remark;

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
