package com.level.transaction.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel("账户商品信息")
@TableName("account_info")
public class AccountInfo {

    @TableId(value = "commodity_id",type = IdType.AUTO)
    @ApiModelProperty(value = "商品id")
    private Integer commodityId;

    @ApiModelProperty(value = "商品名称")
    private String commodityName;

    @ApiModelProperty(value = "所属用户id")
    private Integer userId;

    @ApiModelProperty(value = "账号所属系统类型")
    private String systemType;

    @ApiModelProperty(value = "换绑时间")
    private Date changeTime;

    @ApiModelProperty(value = "第几任号主")
    private String whichTerm;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "是否急售,0否1是")
    private Integer rushSale;

    @ApiModelProperty(value = "是否可砍价,0否1是")
    private Integer bargain;

    @ApiModelProperty(value = "联系方式")
    private String contactInfo;

    @ApiModelProperty(value = "账号名字数")
    private Integer accountWordCount;

    @ApiModelProperty(value = "账号名类型")
    private String accountType;

    @ApiModelProperty(value = "账号属性")
    private String accountAttribute;

    @ApiModelProperty(value = "账号性别")
    private String accountGender;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "已擦亮次数")
    private String polishedNum;

}
