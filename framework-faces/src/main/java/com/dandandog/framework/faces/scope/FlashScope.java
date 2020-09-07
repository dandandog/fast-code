package com.dandandog.framework.faces.scope;

import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author JohnnyLiu
 */
@Component("flashScope")
public class FlashScope implements Map<String, Object> {
    @Override
    public void clear() {
        getFlash().clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return getFlash().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getFlash().containsValue(value);
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return getFlash().entrySet();
    }

    @Override
    public Object get(Object key) {
        return getFlash().get(key);
    }

    @Override
    public boolean isEmpty() {
        return getFlash().isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return getFlash().keySet();
    }

    @Override
    public Object put(String key, Object value) {
        return getFlash().put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        getFlash().putAll(m);
    }

    @Override
    public Object remove(Object key) {
        return getFlash().remove(key);
    }

    @Override
    public int size() {
        return getFlash().size();
    }

    @Override
    public Collection<Object> values() {
        return getFlash().values();
    }

    private Flash getFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }
}
