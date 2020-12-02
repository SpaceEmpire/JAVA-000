package com.liq.ssdemo.mapper;

import com.liq.ssdemo.bean.User;
import org.springframework.stereotype.Repository;

/**
 * Mapper
 *
 * @author liquan
 * date: 2020/12/02 12:21
 * version: 1.0
 */
@Repository
public interface UserMapper {

    User findUserById(int id);

    int updateUserById(User user);
}
