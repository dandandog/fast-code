package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.Oauth2AccessTokenDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 12:54
 */
@Data
public class Oauth2AccessTokenResult extends AbstractApiResponse {

    private Oauth2AccessTokenDTO result;

}
