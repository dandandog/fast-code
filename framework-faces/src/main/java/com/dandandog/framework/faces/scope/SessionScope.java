package com.dandandog.framework.faces.scope;

import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author JohnnyLiu
 */
@Component("sessionScope")
public class SessionScope implements Map<String, Object> {

    @Override
    public void clear() {
        getSessionMap().clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return getSessionMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getSessionMap().containsValue(value);
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return getSessionMap().entrySet();
    }

    @Override
    public Object get(Object key) {
        return getSessionMap().get(key);
    }

    @Override
    public boolean isEmpty() {
        return getSessionMap().isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return getSessionMap().keySet();
    }

    @Override
    public Object put(String key, Object value) {
        return getSessionMap().put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        getSessionMap().putAll(m);
    }

    @Override
    public Object remove(Object key) {
        return getSessionMap().remove(key);
    }

    @Override
    public int size() {
        return getSessionMap().size();
    }

    @Override
    public Collection<Object> values() {
        return getSessionMap().values();
    }

    private Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
}
