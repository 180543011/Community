package com.zhiling.z.community.utils;

import com.zhiling.z.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  工具类，用于使用userService
 * @Author zlhl
 * @Date 2019/12/25
 * @Version V1.0
 **/
@Component
public class UserServiceUtil {
    private static UserService userService = null;

    @Autowired
    public UserServiceUtil(UserService userService) {
        UserServiceUtil.userService = userService;
    }


    public static UserService getUserService(){
        return userService;
    }

}
