/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.level.security.controller;

import com.level.security.dto.LoginDTO;
import com.level.security.service.CaptchaService;
import com.level.security.service.SysUserTokenService;
import com.level.security.user.SecurityUser;
import com.level.transaction.domain.User;
import com.level.transaction.service.UserService;
import com.level.utils.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@Api(tags="登录管理")
public class LoginController {
	@Autowired
	private SysUserTokenService sysUserTokenService;

	@Autowired
	UserService userService;


	@PostMapping("login")
	@ApiOperation(value = "登录")
	public AjaxResult login(HttpServletRequest request, @RequestBody LoginDTO login) {
		//用户信息
		User user = userService.getUserByPhone(login.getPhone());
		return sysUserTokenService.createToken(user.getUserId());
	}

	@PostMapping("logout")
	@ApiOperation(value = "退出")
	public AjaxResult logout(HttpServletRequest request) {
		User user = SecurityUser.getUser();
		//退出
		sysUserTokenService.logout(user.getUserId());
		return new AjaxResult();
	}

}
