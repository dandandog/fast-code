package com.dandandog.framework.wx.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.dandandog.framework.wx.config.WxConfig;
import com.dandandog.framework.wx.jwt.JwtToken;
import com.dandandog.framework.wx.utils.JwtTokenUtil;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author JohnnyLiu
 */
public interface TokenService {



    default String generateToken(String appId, String code) throws WxErrorException {
        WxMaService wxMaService = WxConfig.getMaService(appId);
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
        return generate3rdSession(session.getSessionKey(), session.getOpenid());
    }

    default String refreshToken(JwtToken jwtToken) {
        return JwtTokenUtil.refreshToken(jwtToken.getToken(), jwtToken.getSecret());
    }

    default JwtToken generateJwtToken(String token) {
        String uniqueId = JwtTokenUtil.getUniqueId(token);
        String secret = getSecret(uniqueId);
        return JwtToken.build(token, uniqueId, secret);
    }

    String generate3rdSession(String sessionKey, String openId);

    String getSecret(String uniqueId);


}
