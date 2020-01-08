package com.zhiling.z.community.dto;

/**
 *  回复评论类型
 * @Author zlhl
 * @Date 2020/1/3
 * @Version V1.0
 **/
public enum CommentTypeEnum {

    /**
     * 问题回复,一级回复
     * 回复别人的评论,二级回复
     */
    QUESTION_COMMENT(1,"一级评论"),
    SECOND_COMMENT(2,"二级评论"),
    THIRD_COMMENT(3,"二级子评论");

    private int type;
    private String comment;

    CommentTypeEnum(int type, String comment) {
        this.type = type;
        this.comment = comment;
    }

    public int getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }
}
