package com.liq.ssdemo.service.impl;

import com.liq.ssdemo.bean.User;
import com.liq.ssdemo.mapper.UserMapper;
import com.liq.ssdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author liquan
 * date: 2020/12/02 12:14
 * version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }
}
