
package org.quentin.web.service.impl;

import org.quentin.web.mapper.WebResourceMapper;
import org.quentin.web.dto.WebResource;
import org.quentin.web.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author:quentin
 * @create: 2022-10-10 21:05
 * @Description:
 */
@Service
@CacheConfig(cacheNames = "resources")
public class ResourceServiceImpl implements ResourceService {

    public static final Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private final WebResourceMapper webResourceMapper;

    public ResourceServiceImpl(WebResourceMapper webResourceMapper){
        this.webResourceMapper = webResourceMapper;
    }

    @Override
    @Cacheable
    public List<WebResource> resourceList() {
        return webResourceMapper.webResourceList();
    }

    @Override
    public Map<String, String> resourceMap() {
        List<WebResource> resources = webResourceMapper.webResourceList();
        Map<String, String> resourceMap = new LinkedHashMap<>();
        resources.forEach(
                resource -> resourceMap.put(resource.getResourceName(), resource.getResourceInfo()));
        return resourceMap;
    }

    @Override
    @Cacheable(key = "#id")
//    @Cacheable(keyGenerator = "myKeyGenerator")
    public WebResource resource(Integer id) {
        return webResourceMapper.resourceById(id);
    }
}
