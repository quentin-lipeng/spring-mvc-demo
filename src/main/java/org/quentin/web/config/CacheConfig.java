package org.quentin.web.config;

import org.quentin.web.utils.MyCacheKeyGenerator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author quentin
 */
@Configuration(proxyBeanMethods = false)
// 开启缓存注解
@EnableCaching
public class CacheConfig {
/*    @Bean
    public EhCacheCacheManager cacheManager() {
        EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager();
        cacheCacheManager.setCacheManager(ehcache());
        return cacheCacheManager;
    }*/

/*        @Bean
    public CacheManager ehcache() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        Resource resource = new ClassPathResource("classpath:ehcache.xml");
        factoryBean.setConfigLocation(resource);
        return factoryBean.getObject();
    }*/

    /**
     * TODO 我只注册了ConcurrentMapCacheFactoryBean的bean 但可以获取到ConcurrentMapCache
     * 其中 第三方缓存有 Caffeine Ehcache redis
     * Caffeine: 如果想简单使用 性能最好 由guava重写
     * Ehcache 支持分布式 功能更多
     * redis会有network IO的消耗
     */
    @Bean
    public CacheManager cacheManager(
            ConcurrentMapCache cacheBeanResource) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(
                Collections.singletonList(cacheBeanResource));
        return cacheManager;
    }

    /**
     * todo 替换redis为缓存存储
     */
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
