package org.quentin.web.service;

import org.quentin.web.dto.WebResource;

import java.util.List;
import java.util.Map;

public interface ResourceService {
    List<WebResource> resourceList();

    Map<String, String> resourceMap();

    WebResource resource(Integer id);

}
