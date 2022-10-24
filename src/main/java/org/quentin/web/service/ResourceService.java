package org.quentin.web.service;

import org.quentin.web.pojo.WebResource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ResourceService {
    List<WebResource> resourceList();

    Map<String, String> resourceMap();
}
