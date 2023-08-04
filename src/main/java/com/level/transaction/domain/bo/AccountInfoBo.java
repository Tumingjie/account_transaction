package com.level.transaction.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("查询账户商品信息")
public class AccountInfoBo {

    @ApiModelProperty(value = "商品id")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名称")
    private String commodityName;

    @ApiModelProperty(value = "所属用户id")
    private Integer userId;

    @ApiModelProperty(value = "账号所属系统类型")
    private String systemType;

    @ApiModelProperty(value = "小于等于多少价格")
    private Double price;

    @ApiModelProperty(value = "是否急售,0否1是")
    private Integer rushSale;

    @ApiModelProperty(value = "是否可砍价,0否1是")
    private Integer bargain;

    @ApiModelProperty(value = "账号名字数")
    private Integer accountWordCount;

    @ApiModelProperty(value = "账号名类型")
    private String accountType;

    @ApiModelProperty(value = "账号属性")
    private String accountAttribute;

    @ApiModelProperty(value = "账号性别")
    private String accountGender;
}
