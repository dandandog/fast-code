package com.dandandog.framework.rest.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.dandandog.framework.common.utils.IpUtil;
import com.dandandog.framework.rest.utils.JwtTokenUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.HostAuthenticationToken;

import java.util.Date;

/**
 * Shiro Token登入使用的对象
 *
 * @author JohnnyLiu
 */
@Data
@Accessors(chain = true)
public class JwtToken implements HostAuthenticationToken {

    /**
     * 登录ip
     */
    private String host;
    /**
     * 登录用户名称
     */
    private String uniqueId;
    /**
     * 登录token
     */
    private String token;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 多长时间过期，默认一小时
     */
    private long expires;
    /**
     * 过期日期
     */
    private Date expireDate;


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }


    public static JwtToken build(String token, String uniqueId, String secret) {
        DecodedJWT decodedJwt = JwtTokenUtil.decodeToken(token);
        Date createDate = decodedJwt.getIssuedAt();
        Date expireDate = decodedJwt.getExpiresAt();
        return new JwtToken()
                .setUniqueId(uniqueId)
                .setToken(token)
                .setSecret(secret)
                .setHost(IpUtil.getRequestIp())
                .setCreateDate(createDate)
                .setExpireDate(expireDate);

    }
}
