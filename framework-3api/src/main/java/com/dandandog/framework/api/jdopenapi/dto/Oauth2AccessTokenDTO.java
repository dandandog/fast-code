package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 14:57
 */
@Data
public class Oauth2AccessTokenDTO {

    private String uid;

    private String access_token;

    private String refresh_token;

    private Long time;

    private Integer expires_in;

    private Long refresh_token_expires;

}
