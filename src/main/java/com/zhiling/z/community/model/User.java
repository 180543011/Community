package com.zhiling.z.community.model;

import lombok.Data;

import java.io.Serializable;

/**
 *  用户对象
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String userName;
    private String password;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;
    private String bio;
    private String avatarUrl;

}
