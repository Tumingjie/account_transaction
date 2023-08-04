package com.level.transaction.controller;

import com.level.rocketMq.producer.MessageProducer;
import com.level.transaction.domain.User;
import com.level.transaction.domain.bo.TransactionBo;
import com.level.transaction.domain.bo.UserBo;
import com.level.transaction.service.AccountInfoService;
import com.level.transaction.service.UserService;
import com.level.utils.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户信息")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountInfoService accountInfoService;

    @Autowired
    MessageProducer messageProducer;

    @ApiOperation("查询用户信息")
    @PostMapping("/list")
    public AjaxResult<List<User>> getUserByUserBo(@RequestBody UserBo bo){
        return AjaxResult.success(userService.getUserByUserBo(bo));
    }

    @ApiOperation("交易")
    @PostMapping("/transaction")
    public AjaxResult<Void> transaction(@RequestBody TransactionBo bo){
        messageProducer.sendMsgInTransaction(bo);
        return AjaxResult.success("成功！");
    }
}
