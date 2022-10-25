package org.quentin.web.config;

import org.quentin.web.utils.MyCacheKeyGenerator;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author quentin
 */
@Configuration
// 开启缓存注解
@EnableCaching
public class CacheConfig {
//    @Bean
//    public EhCacheCacheManager cacheManager() {
//        EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager();
//        cacheCacheManager.setCacheManager(ehcache());
//        return cacheCacheManager;
//    }

    //    @Bean
//    public CacheManager ehcache() {
//        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
//        Resource resource = new ClassPathResource("classpath:ehcache.xml");
//        factoryBean.setConfigLocation(resource);
//        return factoryBean.getObject();
//    }

    @Bean
    // TODO 我只注册了ConcurrentMapCacheFactoryBean的bean 但可以获取到ConcurrentMapCache
    public CacheManager cacheManager(
            ConcurrentMapCache cacheBeanResource) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(
                Collections.singletonList(cacheBeanResource));
        return cacheManager;
    }

    @Bean
    public ConcurrentMapCacheFactoryBean cacheBeanResource() {
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("resources");
        return cacheFactoryBean;
    }

    @Bean
    public MyCacheKeyGenerator myKeyGenerator() {
        return new MyCacheKeyGenerator();
    }
}
