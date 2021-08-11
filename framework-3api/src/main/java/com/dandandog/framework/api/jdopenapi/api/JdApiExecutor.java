package com.dandandog.framework.api.jdopenapi.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dandandog.framework.api.config.properties.JdProperties;
import com.dandandog.framework.api.jdopenapi.dto.ResultDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import com.dandandog.framework.api.jdopenapi.entity.QueryExtsAPIRequest;
import com.dandandog.framework.api.jdopenapi.param.Oauth2AccessTokenParam;
import com.dandandog.framework.api.jdopenapi.param.Oauth2RefreshTokenParam;
import com.dandandog.framework.api.jdopenapi.result.Oauth2AccessTokenResult;
import com.dandandog.framework.api.jdopenapi.result.Oauth2RefreshTokenResult;
import com.dandandog.framework.api.jdopenapi.utils.HttpUtil;
import com.dandandog.framework.api.jdopenapi.utils.MD5Util;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

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

    public final <TResponse extends AbstractApiResponse> TResponse execute(AbstractAPIRequest<TResponse> apiRequest, String token) {
        apiRequest.setToken(token);
        String post = HttpUtil.post(BASE_URL + apiRequest.getUrl(), BeanUtil.beanToMap(apiRequest));
        return JSONUtil.toBean(post, apiRequest.getResponseClass());
    }

    public final <TResponse extends AbstractApiResponse> TResponse execute(AbstractAPIRequest<TResponse> apiRequest) {
        Oauth2AccessTokenResult token = getToken();
        apiRequest.setToken(token.getResult().getAccessToken());
        String post = HttpUtil.post(BASE_URL + apiRequest.getUrl(), BeanUtil.beanToMap(apiRequest));

        Map<String, Object> resultExts = null;
        JSONObject jsonObject = JSONUtil.parseObj(post);
        if (apiRequest instanceof QueryExtsAPIRequest) {
            QueryExtsAPIRequest<?> request = (QueryExtsAPIRequest<?>) apiRequest;
            if (StrUtil.isNotBlank(request.getQueryExts())) {
                String[] split = StrUtil.split(request.getQueryExts(), StrUtil.COMMA);
                resultExts = Arrays.stream(split).collect(Collectors.toMap(k -> k, v -> JSONUtil.isJsonArray(jsonObject.getStr("result"))
                        ? jsonObject.getJSONArray("result").stream().map(o -> JSONUtil.parseObj(o).get(v)).toArray() : jsonObject.getJSONObject("result").get(v)));
            }
        }

        TResponse tResponse = JSONUtil.toBean(post, apiRequest.getResponseClass());
        if (ArrayUtil.isArray(tResponse.getResult())) {
            Object[] objs = ArrayUtil.wrap((Object) tResponse.getResult());
            for (Object obj : objs) {
                if (obj instanceof ResultDTO) {
                    ResultDTO dto = (ResultDTO)obj;
                    dto.setResultExts(resultExts);
                }
            }
        } else {
            if (tResponse.getResult() instanceof ResultDTO) {
                ResultDTO dto = tResponse.getResult();
                dto.setResultExts(resultExts);
            }
        }

        return tResponse;
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
        return execute(param, null);
    }


    public final Oauth2RefreshTokenResult refreshToken(String refreshToken) {
        Oauth2RefreshTokenParam param = new Oauth2RefreshTokenParam();
        param.setRefresh_token(refreshToken);
        param.setClient_id(properties.getClientId());
        param.setClient_secret(properties.getClientSecret());
        return execute(param, null);
    }

}
