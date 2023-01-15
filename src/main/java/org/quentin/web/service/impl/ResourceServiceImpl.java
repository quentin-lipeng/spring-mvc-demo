package org.quentin.web.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.quentin.web.mapper.WebResourceMapper;
import org.quentin.web.dto.WebResource;
import org.quentin.web.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author:quentin
 * @create: 2022-10-10 21:05
 * @Description:
 */
@Service
@CacheConfig(cacheNames = "resources")
public class ResourceServiceImpl implements ResourceService {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private final WebResourceMapper mapper;

    private final SqlSession session;

    public ResourceServiceImpl(WebResourceMapper mapper, SqlSession session) {
        this.mapper = mapper;
        this.session = session;
    }

    @Override
    @Cacheable
    public List<WebResource> resourceList() {
//        return webResourceMapper.webResourceList();
        // 使用SqlSession实现查找 但更推荐使用mapper
        return session.selectList("org.quentin.web.mapper.WebResourceMapper.webResourceList");
    }

    @Override
    public Map<String, String> resourceMap() {
        List<WebResource> resources = mapper.webResourceList();
        return resources.stream().collect(Collectors.toMap(WebResource::getResourceName, WebResource::getResourceInfo));
    }

    @Override
    @Cacheable(key = "#id")
//    @Cacheable(keyGenerator = "myKeyGenerator")
    public WebResource resource(Integer id) {
        return mapper.resourceById(id);
    }

    @Override
    public boolean addResource(WebResource resource) {
        int retNum = mapper.insertResource(resource);
        return retNum > 0;
    }

    @Override
    public Map<String, String> getFilterChainMap() {
        return resourceMap();
    }
}
