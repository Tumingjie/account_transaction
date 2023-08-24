package com.level.transaction.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.level.utils.aes.AESEncryptionHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("字典")
@TableName(value = "dict_info",autoResultMap = true)
public class DictInfo {

  @TableId(value = "id",type = IdType.AUTO)
  @ApiModelProperty(value = "字典id")
  private Integer id;

  @ApiModelProperty(value = "字典所属code值")
  @TableField(typeHandler = AESEncryptionHandler.class)
  private String code;

  @ApiModelProperty(value = "字典值")
  private String value;

  @ApiModelProperty(value = "字典值拼音编码")
  private String pinyin;

  @ApiModelProperty(value = "序号")
  private String no;

  @ApiModelProperty(value = "字典描述")
  private String description;

}
