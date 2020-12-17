package com.liq.rpcfx.demo.provider;

import com.liq.rpcfx.demo.api.User;
import com.liq.rpcfx.demo.api.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
