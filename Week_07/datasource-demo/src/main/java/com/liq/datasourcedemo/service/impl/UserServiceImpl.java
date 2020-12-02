package com.liq.datasourcedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liq.datasourcedemo.bean.User;
import com.liq.datasourcedemo.componet.CurDataSource;
import com.liq.datasourcedemo.datasource.DataSourceNames;
import com.liq.datasourcedemo.mapper.UserMapper;
import com.liq.datasourcedemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 * @author liquan
 * date: 2020/12/02 12:14
 * version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @CurDataSource(name = DataSourceNames.SECOND)
    @Override
    public User findById(int id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int updateUserById(User user) {
        return this.baseMapper.updateById(user);
    }
}
