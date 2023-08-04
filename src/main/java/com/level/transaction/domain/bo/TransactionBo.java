package com.level.transaction.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("交易参数")
public class TransactionBo {

    @ApiModelProperty(value = "买家")
    private Integer buyerId;

    @ApiModelProperty(value = "卖家")
    private Integer sellerId;

    @ApiModelProperty(value = "交易金额")
    private Double price;
}
