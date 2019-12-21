package com.zhiling.z.community.service;

import com.zhiling.z.community.model.User;

/**
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
public interface UserService {

    /**
     *  创建用户
     * @param user 用户
     */
    void insertUser(User user);

    /**
     *  通过token获取用户信息
     * @param token token值
     * @return 用户对象
     */
    User getUserByToken(String token);

}
