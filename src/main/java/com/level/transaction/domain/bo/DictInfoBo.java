package com.level.transaction.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("查询字典参数")
public class DictInfoBo {

    @ApiModelProperty(value = "字典id")
    private Integer id;

    @ApiModelProperty(value = "字典所属code值")
    private String code;

    @ApiModelProperty(value = "字典值拼音编码")
    private String pinyin;
}
