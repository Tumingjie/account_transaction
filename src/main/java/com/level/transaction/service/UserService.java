package com.level.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.level.transaction.domain.User;
import com.level.transaction.domain.bo.UserBo;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> getUserByUserBo(UserBo userBo);

    User getUserByPhone(String phone);

    Boolean insertUser(User user);
}
