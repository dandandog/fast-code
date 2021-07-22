package com.dandandog.framework.api.jdopenapi.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import com.dandandog.framework.api.config.properties.JdProperties;
import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import com.dandandog.framework.api.jdopenapi.param.Oauth2AccessTokenParam;
import com.dandandog.framework.api.jdopenapi.param.Oauth2RefreshTokenParam;
import com.dandandog.framework.api.jdopenapi.result.Oauth2AccessTokenResult;
import com.dandandog.framework.api.jdopenapi.result.Oauth2RefreshTokenResult;
import com.dandandog.framework.api.jdopenapi.utils.HttpUtil;
import com.dandandog.framework.api.jdopenapi.utils.MD5Util;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 11:33
 */
public class JdApiExecutor {


    private static JdProperties properties;

    private static final String BASE_URL = "https://bizapi.jd.com";

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public JdApiExecutor(JdProperties properties) {
        JdApiExecutor.properties = properties;
    }

    public final <TResponse extends AbstractApiResponse> TResponse execute(AbstractAPIRequest<TResponse> apiRequest) {
        String post = HttpUtil.post(BASE_URL + apiRequest.getUrl(), BeanUtil.beanToMap(apiRequest, true, true));
        return JSONUtil.toBean(post, apiRequest.getResponseClass());
    }

    public final <TResponse extends AbstractApiResponse> TResponse executeWithToken(AbstractAPIRequest<TResponse> apiRequest) {
        Oauth2AccessTokenResult token = getToken();
        apiRequest.setToken(token.getResult().getAccessToken());
        String post = HttpUtil.post(BASE_URL + apiRequest.getUrl(), BeanUtil.beanToMap(apiRequest));
        return JSONUtil.toBean(post, apiRequest.getResponseClass());
    }

    public final Oauth2AccessTokenResult getToken() {
        Oauth2AccessTokenParam param = new Oauth2AccessTokenParam();

        param.setGrant_type("access_token");
        param.setUsername(properties.getUsername());
        param.setPassword(MD5Util.getMd5Str(properties.getPassword()));
        param.setTimestamp(LocalDateTimeUtil.format(LocalDateTimeUtil.now(), TIME_FORMAT));
        param.setClient_id(properties.getClientId());

        String sign = properties.getClientSecret() + param.getTimestamp() + param.getClient_id() + param.getUsername() + param.getPassword() + param.getGrant_type() + properties.getClientSecret();
        sign = MD5Util.getMd5Str(sign).toUpperCase();

        param.setSign(sign);
        return execute(param);
    }


    public final Oauth2RefreshTokenResult refreshToken(String refreshToken) {
        Oauth2RefreshTokenParam param = new Oauth2RefreshTokenParam();
        param.setRefresh_token(refreshToken);
        param.setClient_id(properties.getClientId());
        param.setClient_secret(properties.getClientSecret());
        return execute(param);
    }

}
