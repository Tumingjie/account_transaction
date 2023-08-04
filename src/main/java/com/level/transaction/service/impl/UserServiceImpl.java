package com.level.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.level.transaction.domain.User;
import com.level.transaction.domain.bo.UserBo;
import com.level.transaction.mapper.UserMapper;
import com.level.transaction.service.UserService;
import com.level.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> getUserByUserBo(UserBo userBo) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(null != userBo.getUserId(),User::getUserId,userBo.getUserId());
        wrapper.eq(StringUtils.isNotEmpty(userBo.getPhone()),User::getPhone,userBo.getPhone());
        wrapper.like(StringUtils.isNotEmpty(userBo.getUserName()),User::getUserName,userBo.getUserName());
        return list(wrapper);
    }

    @Override
    public User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotEmpty(phone),User::getPhone,phone);
        return getOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertUser(User user) {
        return save(user);
    }

}
