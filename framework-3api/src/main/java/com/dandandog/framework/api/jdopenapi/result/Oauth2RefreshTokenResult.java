package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.Oauth2RefreshTokenDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 17:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Oauth2RefreshTokenResult extends AbstractApiResponse {

    Oauth2RefreshTokenDTO result;

}
