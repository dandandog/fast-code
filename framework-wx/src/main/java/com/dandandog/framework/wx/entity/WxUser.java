package com.dandandog.framework.wx.entity;

import lombok.Data;

/**
 * @author JohnnyLiu
 */
@Data
public class WxUser {

    /**
     * 小程序openid
     */
    private String openId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 小程序session
     */
    private String sessionKey;

    /**
     * 密碼
     */
    private String secret;


}
