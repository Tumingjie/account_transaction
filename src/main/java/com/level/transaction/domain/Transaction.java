package com.level.transaction.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("账户商品信息")
@TableName("transaction")
public class Transaction {

    @TableId(value = "id",type = IdType.INPUT)
    @ApiModelProperty(value = "订单id")
    private Integer id;
    
    @ApiModelProperty(value = "买家")
    private Integer buyerId;

    @ApiModelProperty(value = "卖家")
    private Integer sellerId;

    @ApiModelProperty(value = "交易金额")
    private Double price;

}
