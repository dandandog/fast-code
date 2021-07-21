package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetPageNumResult;
import com.dandandog.framework.api.jdopenapi.result.Oauth2AccessTokenResult;
import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 14:13
 */
@Data
public class Oauth2AccessTokenParam extends AbstractAPIRequest<Oauth2AccessTokenResult> {

    private String grantType;

    private String clientId;

    private String timestamp;

    private String username;

    private String password;

    private String sign;

    @Override
    public String getUrl() {
        return "/oauth2/accessToken";
    }

}
