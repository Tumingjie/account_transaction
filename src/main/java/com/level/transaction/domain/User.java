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
@ApiModel("用户表")
@TableName("user")
public class User {
  @TableId(value = "user_id",type = IdType.AUTO)
  @ApiModelProperty(value = "用户id")
  private Integer userId;

  @ApiModelProperty(value = "用户名")
  private String userName;

  @ApiModelProperty(value = "手机号")
  private String phone;

  @ApiModelProperty(value = "上架次数")
  private Integer shelfTimes;

  @ApiModelProperty(value = "头像url")
  private String profileUrl;

  @ApiModelProperty(value = "订阅账号名字数")
  private Integer subscribeWordCount;

  @ApiModelProperty(value = "订阅账号名类型")
  private String subscribeType;

  @ApiModelProperty(value = "订阅账号属性")
  private String subscribeAttribute;

  @ApiModelProperty(value = "订阅账号性别")
  private String subscribeGender;

  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  @ApiModelProperty(value = "更新时间")
  private Date updateTime;

}
