package com.dandandog.framework.faces.scope;

import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author JohnnyLiu
 */
@Component("pageScope")
public class PageScope implements Map<String, Object> {

    private static final String PAGE_SCOPE_PREFIX = "PAGE";

    @Override
    public void clear() {
        getPageMap().clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return getPageMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getPageMap().containsValue(value);
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return getPageMap().entrySet();
    }

    @Override
    public Object get(Object key) {
        return getPageMap().get(key);
    }

    @Override
    public boolean isEmpty() {
        return getPageMap().isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return getPageMap().keySet();
    }

    @Override
    public Object put(String key, Object value) {
        return getPageMap().put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        getPageMap().putAll(m);
    }

    @Override
    public Object remove(Object key) {
        return getPageMap().remove(key);
    }

    @Override
    public int size() {
        return getPageMap().size();
    }

    @Override
    public Collection<Object> values() {
        return getPageMap().values();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getPageMap() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Map<String, Object> pageMap = (Map<String, Object>) sessionMap.get(PAGE_SCOPE_PREFIX + viewId);
        if (pageMap == null) {
            pageMap = new HashMap<>();
            sessionMap.put(PAGE_SCOPE_PREFIX + viewId, pageMap);
        }
        return pageMap;
    }
}
