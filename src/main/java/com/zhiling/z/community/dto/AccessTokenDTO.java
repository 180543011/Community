package com.zhiling.z.community.dto;

import lombok.Data;

/**
 *  用于保存GitHub数据
 * @author zlhl
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
