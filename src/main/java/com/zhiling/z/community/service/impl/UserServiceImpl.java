package com.zhiling.z.community.service.impl;

import com.zhiling.z.community.dao.UserMapper;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserByToken(String token) {
        return userMapper.getUserByToken(token);
    }
}
