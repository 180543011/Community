package com.zhiling.z.community.service.impl;

import com.zhiling.z.community.controller.exception.CustomizeException;
import com.zhiling.z.community.dao.UserMapper;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User login(User user) {
        //判断用户名密码不为空
        if (user.getUserName()!=null && !"".equals(user.getUserName()) &&
                user.getPassword()!=null && !"".equals(user.getPassword())){
            return userMapper.getUserByUserName(user.getUserName());
        }else {
            return null;
        }
    }

    @Override
    public String register(User user) {
        if (user.getUserName()!=null && !"".equals(user.getUserName()) &&
                user.getPassword()!=null && !"".equals(user.getPassword())){
            User userByUserName = userMapper.getUserByUserName(user.getUserName());
            //判断是否存在该用户名
            if (userByUserName == null){
                //不存在该用户
                user.setToken(UUID.randomUUID().toString());
                if (user.getName() == null || "".equals(user.getName())){
                    user.setName("admin");
                }
                if (user.getAvatarUrl() == null || "".equals(user.getAvatarUrl())){
                    user.setAvatarUrl("/images/default.jpg");
                }
                user.setBio("这家伙很懒，没有留下任何东西");
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModify(user.getGmtCreate());
                int i = userMapper.insertUser(user);
                if (i > 0){
                    return user.getToken();
                }else {
                    throw new CustomizeException("系统繁忙，添加失败，请稍后再试");
                }
            }
        }
        return null;
    }

    @Override
    public User getUserByToken(String token) {
        return userMapper.getUserByToken(token);
    }

    @Override
    public User getUserByAccountId(String accountId) {
        return userMapper.getUserByAccountId(accountId);
    }
}
