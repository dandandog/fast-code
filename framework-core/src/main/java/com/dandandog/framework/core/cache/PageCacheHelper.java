package com.dandandog.framework.core.cache;

import org.springframework.cache.Cache;

/**
 * @author JohnnyLiu
 */
public class PageCacheHelper {

    public boolean canEvict(Cache cache) {
        String cacheUser = cache.getName();
        return true;
    }
}
