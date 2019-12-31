package com.zhiling.z.community.controller.exception;

/**
 *  自定义异常类
 * @Author zlhl
 * @Date 2019/12/25
 * @Version V1.0
 **/
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
