/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.level.security.service.impl;

import com.level.security.mapper.SysUserTokenDao;
import com.level.security.domain.SysUserTokenEntity;
import com.level.security.service.ShiroService;
import com.level.transaction.domain.User;
import com.level.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserService userService;

    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public SysUserTokenEntity getByToken(String token) {
        return sysUserTokenDao.getByToken(token);
    }

    @Override
    public User getUser(Integer userId) {
        return userService.getById(userId);
    }
}
