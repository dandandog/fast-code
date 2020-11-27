package com.dandandog.framework.wx.jwt;

import com.dandandog.framework.wx.exception.WxTokenException;
import com.dandandog.framework.wx.model.WxErrorCode;
import com.dandandog.framework.wx.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author JohnnyLiu
 */
@Slf4j
public class JwtCredentialsMatcher implements CredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = authenticationToken.getCredentials().toString();
        String salt = authenticationInfo.getCredentials().toString();
        try {
            return JwtTokenUtil.verifyToken(token, salt);
        } catch (Exception e) {
            throw new WxTokenException(WxErrorCode.JWT_DECODE_EXCEPTION);
        }
    }
}
