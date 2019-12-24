package com.zhiling.z.community.dto;

import lombok.Data;

/**
 *  GitHub对象所需值
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
