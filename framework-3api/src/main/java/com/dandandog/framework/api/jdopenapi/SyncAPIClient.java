package com.dandandog.framework.api.jdopenapi;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import com.dandandog.framework.api.jdopenapi.result.Oauth2AccessTokenResult;
import com.dandandog.framework.api.jdopenapi.utils.HttpUtil;
import com.dandandog.framework.api.jdopenapi.utils.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 14:43
 */
public class SyncAPIClient {

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String BASE_URL = "https://bizapi.jd.com";

    private static final String ACCESS_TOKEN_URL = BASE_URL + "/oauth2/accessToken";

    private static final String REFRESH_TOKEN_URL = BASE_URL + "/oauth2/refreshToken";

    public Oauth2AccessTokenResult getToken(String username, String password, String clientId, String clientSecret) {
        Map<String, Object> params = new HashMap<>(6);
        String grantType = "access_token";
        params.put("grant_type", grantType);
        params.put("username", username);
        password = MD5Util.getMd5Str(password);
        params.put("password", password);
        String timestamp = LocalDateTimeUtil.format(LocalDateTimeUtil.now(), TIME_FORMAT);
        params.put("timestamp", timestamp);
        params.put("client_id", clientId);
        String sign = clientSecret + timestamp + clientId + username + password + grantType + clientSecret;
        sign = MD5Util.getMd5Str(sign).toUpperCase();
        params.put("sign", sign);
        String res = HttpUtil.post(ACCESS_TOKEN_URL, params);
        return JSONUtil.toBean(res, Oauth2AccessTokenResult.class);
    }

    public Oauth2AccessTokenResult refreshToken(String refreshToken, String clientId, String clientSecret) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("refresh_token", refreshToken);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        String res = HttpUtil.post(REFRESH_TOKEN_URL, params);
        return JSONUtil.toBean(res, Oauth2AccessTokenResult.class);
    }


}
