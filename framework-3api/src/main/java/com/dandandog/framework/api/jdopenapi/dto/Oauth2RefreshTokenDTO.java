package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 17:22
 */
@Data
public class Oauth2RefreshTokenDTO {

    private String uid;

    private String access_token;

    private String refresh_token;

    private Long time;

    private Integer expires_in;

    private Long refresh_token_expires;

}
