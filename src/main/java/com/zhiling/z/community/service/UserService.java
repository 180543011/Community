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
     * @return 增加记录数
     */
    int insertUser(User user);

    /**
     *  登录用户，登录成功返回登录对象
     * @param user 用户登录信息
     * @return 用户对象
     */
    User login(User user);

    /**
     *  注册用户，注册成功返回Token
     * @param user 用户
     * @return 用户对象
     */
    String register(User user);

    /**
     *  通过token获取用户信息
     * @param token token值
     * @return 用户对象
     */
    User getUserByToken(String token);

    /**
     *  通过API识别码获取对象
     * @param accountId API识别码
     * @return User对象
     */
    User getUserByAccountId(String accountId);

}
