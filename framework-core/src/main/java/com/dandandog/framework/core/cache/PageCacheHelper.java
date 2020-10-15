package com.dandandog.framework.core.cache;

import org.springframework.data.redis.cache.RedisCache;

/**
 * @author JohnnyLiu
 */
public class PageCacheHelper {

    public static boolean canEvict(RedisCache cache) {
        String cacheUser = cache.getName();
        return true;
    }
}
