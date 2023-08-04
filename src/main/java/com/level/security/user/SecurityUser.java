/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.level.security.user;

import com.level.transaction.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public class SecurityUser {

    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取用户信息
     */
    public static User getUser() {
        Subject subject = getSubject();
        if(subject == null){
            return new User();
        }

        User user = (User)subject.getPrincipal();
        if(user == null){
            return new User();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Integer getUserId() {
        return getUser().getUserId();
    }
}
