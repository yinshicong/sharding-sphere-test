package com.example.shardingspheretest.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: yinsc
 * @Date: 2020/11/28
 * @Description:
 */
@Data
public class OrderParam {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("订单编号")
    private String orderNo;

    @ApiModelProperty("支付金额")
    private BigDecimal payPrice;

}
