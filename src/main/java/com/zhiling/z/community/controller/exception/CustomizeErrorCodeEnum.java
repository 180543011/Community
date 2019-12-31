package com.zhiling.z.community.controller.exception;

/**
 * @Author zlhl
 * @Date 2019/12/26
 * @Version V1.0
 **/
public enum CustomizeErrorCodeEnum implements ICustomizeErrorCode {

    /**
     *  没有找到问题
     */
    QUESTION_NOT_FOUND("没有找到信息， 系统异常");

    private String message;

    CustomizeErrorCodeEnum(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
