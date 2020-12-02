package com.liq.datasourcedemo.service;

import com.liq.datasourcedemo.bean.User;

/**
 * UserService
 * @author liquan
 * date: 2020/12/02 12:13
 * version: 1.0
 */
public interface UserService {

    /**
     * 根据id 查询
     * @param id 用户id
     * @return 返回user
     */
    User findById(int id);

    /**
     * 根据id修改
     * @param user user
     * @return 返回更新状态
     */
    int updateUserById(User user);
}
