package com.zhiling.z.community.model;

import java.io.Serializable;

/**
 *  用户对象
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
public class User implements Serializable {
    private Integer id;
    private String name;
    private String userName;
    private String password;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Long getGmtCreate() {
        return gmtCreate;
    }
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    public Long getGmtModify() {
        return gmtModify;
    }
    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountId='" + accountId + '\'' +
                ", token='" + token + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
