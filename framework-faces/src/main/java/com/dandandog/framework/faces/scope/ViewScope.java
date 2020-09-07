package com.dandandog.framework.faces.scope;

import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author JohnnyLiu
 */
@Component("viewScope")
public class ViewScope implements Map<String, Object> {

    @Override
    public void clear() {
        getViewMap().clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return getViewMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getViewMap().containsValue(value);
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return getViewMap().entrySet();
    }

    @Override
    public Object get(Object key) {
        return getViewMap().get(key);
    }

    @Override
    public boolean isEmpty() {
        return getViewMap().isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return getViewMap().keySet();
    }

    @Override
    public Object put(String key, Object value) {
        return getViewMap().put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        getViewMap().putAll(m);
    }

    @Override
    public Object remove(Object key) {
        return getViewMap().remove(key);
    }

    @Override
    public int size() {
        return getViewMap().size();
    }

    @Override
    public Collection<Object> values() {
        return getViewMap().values();
    }

    private Map<String, Object> getViewMap() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
    }

}
