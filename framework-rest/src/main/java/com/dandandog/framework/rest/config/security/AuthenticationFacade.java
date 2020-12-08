package com.dandandog.framework.rest.config.security;

import com.dandandog.framework.common.components.security.IAuthenticationFacade;
import com.dandandog.framework.rest.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    @Override
    public String getUniqueId() {
        try {
            String principal = (String) getPrincipal();
            if (principal != null) {
                return JwtTokenUtil.getUniqueId(principal);
            }
        } catch (UnavailableSecurityManagerException | InvalidSessionException ignored) {
            log.error("uniqueId 获取失败");
        }
        return null;
    }

    @Override
    public boolean isLogin() {
        getSubject().getSession();
        return false;
    }

    @Override
    public Object getPrincipal() {
        return getSubject().getPrincipal();
    }

}
