package com.dandandog.framework.wx.service;

import com.dandandog.framework.wx.jwt.JwtToken;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author JohnnyLiu
 */
public interface WxTokenService {


    /**
     * 刷新token
     *
     * @param jwtToken JwtToken对象
     * @return 返回新的token
     */
    String refreshToken(JwtToken jwtToken);

    /**
     * 创建token
     *
     * @param sessionKey 微信登入sessionKey ,
     * @return 返回新的token
     */
    String generateToken(String sessionKey, String openId) throws WxErrorException;

    /**
     * 构建build
     *
     * @param token 请求中获取的token
     * @return 返回 JwtToken对象
     */
    JwtToken buildJwtToken(String token);


}
