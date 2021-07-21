package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.Oauth2RefreshTokenResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 17:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Oauth2RefreshTokenParam extends AbstractAPIRequest<Oauth2RefreshTokenResult> {

    private String refreshToken;

    private String clientId;

    private String clientSecret;

    @Override
    public String getUrl() {
        return "/oauth2/refreshToken";
    }
}
