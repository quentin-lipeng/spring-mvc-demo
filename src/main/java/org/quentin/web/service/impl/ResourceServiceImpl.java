/**
 * @author:quentin
 * @create: 2022-10-10 21:05
 * @Description:
 */
package org.quentin.web.service.impl;

import org.quentin.web.mapper.WebResourceMapper;
import org.quentin.web.pojo.WebResource;
import org.quentin.web.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {

    public static final Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Resource
    private WebResourceMapper webResourceMapper;

    @Override
    public List<WebResource> resourceList() {
        return webResourceMapper.webResourceList();
    }

    @Override
    public Map<String, String> resourceMap() {
        List<WebResource> resources = webResourceMapper.webResourceList();
        Map<String, String> resourceMap = new LinkedHashMap<>();
        resources.forEach(resource -> {
            resourceMap.put(resource.getResourceName(), resource.getResourceInfo());
        });
        LOG.info("class = " + resourceMap.getClass());
        return resourceMap;
    }
}
