package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 17:22
 */
@Data
public class Oauth2RefreshTokenDTO {

    private String uid;

    private String accessToken;

    private String refreshToken;

    private Long time;

    private Integer expiresIn;

    private Long refreshTokenExpires;

}
