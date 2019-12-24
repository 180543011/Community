package com.zhiling.z.community.model;

import lombok.Data;

/**
 * @Author zlhl
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Data
public class Question {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

    private User user;

}
