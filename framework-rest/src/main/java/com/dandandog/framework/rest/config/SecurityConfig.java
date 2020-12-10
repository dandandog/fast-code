package com.dandandog.framework.rest.config;

import cn.hutool.json.JSONUtil;
import com.dandandog.framework.rest.jwt.JwtCredentialsMatcher;
import com.dandandog.framework.rest.jwt.JwtFilter;
import com.dandandog.framework.rest.jwt.JwtRealm;
import com.dandandog.framework.rest.service.JwtTokenService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "fast-code.jwt", value = {"enabled"}, matchIfMissing = true)
public class SecurityConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilter(JwtTokenService tokenService) {
        // 必须配置 SecurityManager
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setFilters(filterMap(tokenService));
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition(tokenService).getFilterChainMap());
        return shiroFilterFactoryBean;
    }

    /**
     * shiro  filter
     *
     * @return Map
     */
    private Map<String, Filter> filterMap(JwtTokenService tokenService) {
        Map<String, Filter> filters = new HashMap<>(1);
        filters.put("jwt", new JwtFilter(tokenService));
        return filters;
    }

    /**
     * URL拦截连
     *
     * @return ShiroFilterChainDefinition
     */
    @Bean

    public ShiroFilterChainDefinition shiroFilterChainDefinition(JwtTokenService tokenService) {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/swagger-resources/**", "anon");
        chainDefinition.addPathDefinition("/favicon.ico", "anon");
        chainDefinition.addPathDefinition("/api-docs", "anon");
        chainDefinition.addPathDefinition("/doc.html", "anon");
        chainDefinition.addPathDefinition("/api-docs-ext", "anon");
        chainDefinition.addPathDefinition("/webjars/**", "anon");
        chainDefinition.addPathDefinition("/v2/**", "anon");
        chainDefinition.addPathDefinition("/swagger-ui.html", "anon");
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinitions(tokenService.tokenFilterList());
        chainDefinition.addPathDefinition("/**", "jwt");

        log.debug("Shiro intercept path:{}", JSONUtil.toJsonPrettyStr(chainDefinition.getFilterChainMap()));
        return chainDefinition;
    }

    /**
     * securityManager
     *
     * @return SecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator());
        securityManager.setSubjectDAO(closeSubjectDAO());
        return securityManager;
    }

    /**
     * 关闭 ShiroDAO 功能
     *
     * @return DefaultSubjectDAO
     */
    private DefaultSubjectDAO closeSubjectDAO() {
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        // 不需要将 Shiro Session 中的东西存到任何地方（包括 Http Session 中）
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        return subjectDAO;
    }

    @Bean
    public ModularRealmAuthenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setRealms(Lists.newArrayList(jwtRealm()));
        // 设置多 Realm的认证策略，默认 AtLeastOneSuccessfulStrategy
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    public JwtRealm jwtRealm() {
        JwtRealm realm = new JwtRealm();
        realm.setCachingEnabled(false);
        realm.setCredentialsMatcher(new JwtCredentialsMatcher());
        return realm;
    }


    /**
     * 交由 Spring 来自动地管理 Shiro-Bean 的生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 为 Spring-Bean 开启对 Shiro 注解的支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
