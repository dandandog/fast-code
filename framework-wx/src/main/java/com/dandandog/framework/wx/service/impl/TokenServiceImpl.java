package com.dandandog.framework.wx.service.impl;

import com.dandandog.framework.wx.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * @author JohnnyLiu
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String tokenGenerate(String appId, String code) {
        return null;
    }

    private String generate3rdSession(String sessionKey, String openId) {

        return "";
    }


}
