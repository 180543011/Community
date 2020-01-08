package com.zhiling.z.community.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * @author zlhl
 */
@ApiModel(value="com-zhiling-z-community-model-Comment")
@Data
public class Comment implements Serializable {
    /**
    * 评论id
    */
    @ApiModelProperty(value="评论id")
    private Long id;

    /**
     * 回复问题id
     */
    @ApiModelProperty(value="回复问题id")
    private Long questionId;

    /**
    * 评论父id
    */
    @ApiModelProperty(value="评论父id")
    private Long parentId;

    /**
    * 父类类型
    */
    @ApiModelProperty(value="父类类型")
    private Integer type;

    /**
    * 评论人id
    */
    @ApiModelProperty(value="评论人id")
    private Integer commentator;

    /**
    * 评论内容
    */
    @ApiModelProperty(value="评论内容")
    private String context;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Long gmtCreate;

    /**
    * 修改时间
    */
    @ApiModelProperty(value="修改时间")
    private Long gmtModify;

    /**
    * 点赞数
    */
    @ApiModelProperty(value="点赞数")
    private Integer likeCount;

    /**
     *  评论人
     */
    @ApiModelProperty(value="评论人")
    private User user;

}