package com.dandandog.framework.faces.config.rewrite;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.config.properties.PageProperties;
import lombok.AllArgsConstructor;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @author JohnnyLiu
 */
@Component
@AllArgsConstructor
@EnableConfigurationProperties(PageProperties.class)
public class PageRewriteProvider extends HttpConfigurationProvider {

    private PageProperties properties;

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/")))
                .perform(Redirect.temporary(context.getContextPath() + properties.getIndex()))
                .addRule(Join.path(properties.getLogin()).to(StrUtil.addSuffixIfNot(properties.getLogin(), ".faces")))
                .addRule(Join.path(properties.getIndex()).to(StrUtil.addSuffixIfNot(properties.getIndex(), ".faces")))
                .addRule(Join.path(properties.getAccess()).to(StrUtil.addSuffixIfNot(properties.getAccess(), ".faces")))
                .addRule(Join.path(properties.getError()).to(StrUtil.addSuffixIfNot(properties.getError(), ".faces")))
                .addRule(Join.path(properties.getNotFound()).to(StrUtil.addSuffixIfNot(properties.getNotFound(), ".faces")))
                .addRule(Join.path("/{path}").to(StrUtil.addSuffixIfNot("/{path}", ".faces")))
                .addRule(Join.path("/{path}?{params}").to(StrUtil.addSuffixIfNot("/{path}", ".faces?{params}")))
                .addRule(Join.path("/app/{path}").to(StrUtil.addSuffixIfNot("/app/{path}", ".faces")))
                .addRule(Join.path("/app/{path}?{params}").to(StrUtil.addSuffixIfNot("/app/{path}", ".faces?{params}")))
                .addRule(Join.path("/auth/{path}").to(StrUtil.addSuffixIfNot("/auth/{path}", ".faces")))
                .addRule(Join.path("/auth/{path}?{params}").to(StrUtil.addSuffixIfNot("/auth/{path}", ".faces?{params}")))
                .addRule(Join.path("/gift/{path}").to(StrUtil.addSuffixIfNot("/gift/{path}", ".faces")))
                .addRule(Join.path("/gift/{path}?{params}").to(StrUtil.addSuffixIfNot("/gift/{path}", ".faces?{params}")))
                .addRule(Join.path("/mall/prod/{path}").to(StrUtil.addSuffixIfNot("/mall/prod/{path}", ".faces")))
                .addRule(Join.path("/mall/prod/{path}?{params}").to(StrUtil.addSuffixIfNot("/mall/prod/{path}", ".faces?{params}")))
                .addRule(Join.path("/mall/order/{path}").to(StrUtil.addSuffixIfNot("/mall/order/{path}", ".faces")))
                .addRule(Join.path("/mall/order/{path}?{params}").to(StrUtil.addSuffixIfNot("/mall/order/{path}", ".faces?{params}")))

                ;
    }

    @Override
    public int priority() {
        return 10;
    }
}
