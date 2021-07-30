package com.dandandog.framework.faces.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.scope.FlashScope;
import com.dandandog.framework.faces.scope.PageScope;
import com.dandandog.framework.faces.scope.SessionScope;
import com.dandandog.framework.faces.scope.ViewScope;
import com.dandandog.framework.faces.utils.MessageUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * @author JohnnyLiu
 */
@Component
public class FacesController {
    @Autowired
    private FlashScope flashScope;

    @Autowired
    private ViewScope viewScope;

    @Autowired
    private PageScope pageScope;

    @Autowired
    private SessionScope sessionScope;

    @Autowired
    private MessageSource messageSource;


    public void onEntry() {

    }

    public void close(UIComponent component) {
        resetForm(component);
    }

    public void cancel(UIComponent component) {
    }

    public static FacesController getCurrentInstance() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String path = facesContext.getExternalContext().getRequestServletPath();
        ApplicationContext applicationContext = FacesContextUtils.getRequiredWebApplicationContext(facesContext);
        try {
            return applicationContext.getBean(path, FacesController.class);
        } catch (NoSuchBeanDefinitionException ignored) {
        }
        return null;
    }

    public void putFlashScope(String name, Object obj) {
        this.flashScope.put(name, obj);
    }

    public <T> T getFlashScope(String name) {
        return (T) this.flashScope.get(name);
    }

    public void putViewScope(String name, Object obj) {
        this.viewScope.put(name, obj);
    }

    public <T> T getViewScope(String name) {
        return (T) this.viewScope.get(name);
    }

    public void putSessionScope(String name, Object obj) {
        this.sessionScope.put(name, obj);
    }

    public <T> T getSessionScope(String name) {
        return (T) this.sessionScope.get(name);
    }

    public void putPageScope(String name, Object object) {
        this.pageScope.put(name, object);
    }

    public <T> T getPageScope(String name) {
        return (T) this.pageScope.get(name);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public HttpServletResponse getResponse() {

        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public void redirectInternal(String url) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirectExternal(externalContext.getRequestContextPath() + url);
    }

    public void redirectExternal(String url) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(url);
        } catch (IOException e) {
            throw new FacesException(e);
        }
    }

    public String getRequestParameter(String key) {
        Map<String, String> paramValues = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap();
        return paramValues.get(key);
    }

    public void resetForm(UIComponent component) {
        UIForm form = getNestingForm(component);
        if (form != null) {
            VisitContext visitContext = VisitContext.createVisitContext(FacesContext.getCurrentInstance());
            form.visitTree(visitContext, (VisitContext context, UIComponent target) -> {
                if ((target instanceof EditableValueHolder)) {
                    EditableValueHolder holder = (EditableValueHolder) target;
                    holder.resetValue();
                }
                return VisitResult.ACCEPT;
            });
        }
    }


    public UIForm getNestingForm(UIComponent component) {
        UIComponent parent = component.getParent();
        while (parent != null) {
            if ((parent instanceof UIForm)) {
                return (UIForm) parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    public Locale getLocale() {
        return getResponse().getLocale();
    }

    public String getMessage(String code, Object... args) {
        return MessageUtil.getMessageSource(code, args);
    }

    public String getMessage(String prefix, String code, Object... args) {
        return MessageUtil.getMessageSource(prefix, code, args);
    }

    protected void addMessages(String code, FacesMessage.Severity severity, Object... args) {
        FacesMessage facesMessage = new FacesMessage(getMessage(code, args));
        facesMessage.setSeverity(severity);
        MessageUtil.addFacesMessage("messages", facesMessage);
    }

    protected void infoMessages(String code, Object... args) {
        this.addMessages(code, FacesMessage.SEVERITY_INFO, args);
    }

    protected void warnMessages(String code, Object... args) {
        this.addMessages(code, FacesMessage.SEVERITY_WARN, args);
    }

    protected void errorMessages(String code, Object... args) {
        this.addMessages(code, FacesMessage.SEVERITY_ERROR, args);
    }

    protected void fatalMessages(String code, Object... args) {
        this.addMessages(code, FacesMessage.SEVERITY_FATAL, args);
    }

    protected Optional<String> getOptParams(String key) {
        String value = ObjectUtil.defaultIfNull(getViewScope(key), getRequestParameter(key));
        return Optional.ofNullable(value);
    }

}
