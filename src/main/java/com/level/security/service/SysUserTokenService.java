/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.level.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.level.security.domain.SysUserTokenEntity;
import com.level.utils.domain.AjaxResult;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	AjaxResult createToken(Integer userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(Integer userId);

}
