package com.dandandog.framework.rest.service;

import com.dandandog.framework.rest.exception.JwtTokenException;
import com.dandandog.framework.rest.jwt.JwtToken;

import java.util.Map;

/**
 * @author JohnnyLiu
 */
public interface JwtTokenService {


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
    String generateToken(String sessionKey, String openId) throws JwtTokenException;

    /**
     * 构建build
     *
     * @param token 请求中获取的token
     * @return 返回 JwtToken对象
     */
    JwtToken buildJwtToken(String token);


    /**
     * token白名单
     *
     * @return 返回 JwtToken对象
     */
    Map<String, String> tokenFilterList();

}
