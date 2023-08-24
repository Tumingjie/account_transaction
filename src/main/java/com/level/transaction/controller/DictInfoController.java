package com.level.transaction.controller;

import com.level.transaction.domain.DictInfo;
import com.level.transaction.domain.bo.DictInfoBo;
import com.level.transaction.service.DictInfoService;
import com.level.utils.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "字典数据")
@RestController
@RequestMapping("dictInfo")
public class DictInfoController {

    @Autowired
    DictInfoService dictInfoService;

    @ApiOperation("查询字典信息列表")
    @PostMapping("/list")
    public AjaxResult<List<DictInfo>> getDictInfoList(@RequestBody DictInfoBo bo){
        return AjaxResult.success(dictInfoService.getDictInfos(bo));
    }

    @ApiOperation("查询字典信息列表")
    @PostMapping("/saveDictInfo")
    public AjaxResult<Void> saveDictInfo(@RequestBody DictInfo info){
        boolean done = dictInfoService.saveDictInfo(info);
        return done ? AjaxResult.success("成功！") : AjaxResult.error("失败！");
    }

}
