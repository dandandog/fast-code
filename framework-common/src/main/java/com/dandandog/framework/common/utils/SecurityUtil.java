package com.dandandog.framework.common.utils;

import com.dandandog.framework.common.components.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author JohnnyLiu
 */
@Component
public class SecurityUtil {


    private static IAuthenticationFacade authenticationFacade;

    @Autowired(required = false)
    public SecurityUtil(IAuthenticationFacade authenticationFacade) {
        SecurityUtil.authenticationFacade = authenticationFacade;
    }

    public SecurityUtil() {

    }

    public static Optional<String> getOptUniqueId() {
        return Optional.ofNullable(authenticationFacade).map(IAuthenticationFacade::getUniqueId);
    }

    public static Optional<Object> getOptPrincipal() {
        return Optional.ofNullable(authenticationFacade).map(IAuthenticationFacade::getPrincipal);
    }

    public static Optional<Boolean> isOptLogin() {
        return Optional.ofNullable(authenticationFacade).map(IAuthenticationFacade::isLogin);
    }

    public static String getUniqueId() {
        return getOptUniqueId().orElse(null);
    }

    public static Object getPrincipal() {
        return getOptPrincipal().orElse(null);
    }

    public static boolean isLogin() {
        return isOptLogin().orElse(false);
    }


}
