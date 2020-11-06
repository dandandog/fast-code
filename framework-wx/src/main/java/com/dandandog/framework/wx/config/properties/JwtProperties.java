package com.dandandog.framework.wx.config.properties;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author JohnnyLiu
 */
@Data
@Component
@ConfigurationProperties(prefix = "fast-code.jwt")
public class JwtProperties {

    /**
     * 密钥
     */
    private String secret = FastCodeConstant.JWT_DEFAULT_SECRET;

    /**
     * 到期时间
     */
    private Long expire = FastCodeConstant.JWT_DEFAULT_EXPIRE;

    /**
     * taken 头标记
     */
    private String tokenHeader = FastCodeConstant.JWT_TOKEN_HEADER;


    /**
     * taken 启动刷新
     */
    private boolean enableRefresh = true;


    private String uniqueId = FastCodeConstant.JWT_UNIQUE_ID;

    /**
     * 签发人
     */
    private String issuer;

    /**
     * 主题
     */
    private String subject;

    /**
     * 签发的目标
     */
    private String audience;

    /**
     * 刷新token倒计时，默认10分钟，10*60=600
     */
    private Integer refreshTokenCountdown = FastCodeConstant.JWT_TOKEN_REFRESH_COUNTDOWN;


}