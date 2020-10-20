package com.dandandog.framework.common.components.security;


public interface IAuthenticationFacade {


    Object getPrincipal();

    String getUniqueId();

    boolean isLogin();
}
