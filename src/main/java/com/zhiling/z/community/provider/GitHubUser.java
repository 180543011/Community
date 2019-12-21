package com.zhiling.z.community.provider;

import org.springframework.stereotype.Component;

/**
 *  GitHub对象所需值
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Component
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
