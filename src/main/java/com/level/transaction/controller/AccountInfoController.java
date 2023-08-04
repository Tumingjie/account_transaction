package com.level.transaction.controller;

import com.level.transaction.domain.AccountInfo;
import com.level.transaction.domain.bo.AccountInfoBo;
import com.level.transaction.service.AccountInfoService;
import com.level.utils.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "账户商品信息")
@RestController
@RequestMapping("accountInfo")
public class AccountInfoController {

    @Autowired
    AccountInfoService accountInfoService;

    @ApiOperation("查询账户商品信息列表")
    @PostMapping("/list")
    public AjaxResult<List<AccountInfo>> getAccountInfoList(AccountInfoBo bo){
        return AjaxResult.success(accountInfoService.getAccountInfos(bo));
    }

}
