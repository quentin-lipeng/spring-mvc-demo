
package org.quentin.web.controller;

import org.quentin.web.pojo.RetMessage;
import org.quentin.web.pojo.WebResource;
import org.quentin.web.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author:quentin
 * @create: 2022-10-10 20:57
 * @Description:
 */
@Controller
// TODO 路径是否加上/ 斜杠
@RequestMapping("/resource")
public class WebResourceController {
    public static final Logger LOG = LoggerFactory.getLogger(WebResourceController.class);

    private final ResourceService resourceService;

    @GetMapping("")
    public String resourcePage() {
        return "resource";
    }

    // TODO 看文档查看一下 注入域的依赖和构造器方式注入依赖的区别
    // 下面如果是多参可以写多个@Qualifier
    // 其中Qualifier是可选的
    @Autowired
    public WebResourceController(
            @Qualifier(value = "resourceServiceImpl") ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/list")
    public ResponseEntity<RetMessage<List<WebResource>>> getResourceList() {

        List<WebResource> resources = resourceService.resourceList();

        return ResponseEntity.status(HttpStatus.OK).body(
                new RetMessage<List<WebResource>>().data(resources));
    }

    @GetMapping("/map")
    public ResponseEntity<Map<String, String>> getResourceMap() {
        Map<String, String> resourceMap = resourceService.resourceMap();
        return ResponseEntity.ok().body(resourceMap);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<WebResource> getResource(
            @PathVariable Integer id) {
        WebResource resource = resourceService.resource(id);
        return ResponseEntity.ok(resource);
    }

    // TODO 待完善添加资源路径功能
}
