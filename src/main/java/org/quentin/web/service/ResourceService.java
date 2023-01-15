package org.quentin.web.service;

import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.quentin.web.dto.WebResource;

import java.util.List;
import java.util.Map;

public interface ResourceService extends ShiroFilterChainDefinition {
    List<WebResource> resourceList();

    Map<String, String> resourceMap();

    WebResource resource(Integer id);

    boolean addResource(WebResource resource);
}
