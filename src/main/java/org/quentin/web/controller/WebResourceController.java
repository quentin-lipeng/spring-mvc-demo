package org.quentin.web.controller;

import org.quentin.web.pojo.RetMessage;
import org.quentin.web.dto.WebResource;
import org.quentin.web.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:quentin
 * @create: 2022-10-10 20:57
 * @Description:
 */
@Controller
@RequestMapping("/resource")
public class WebResourceController {
    public static final Logger LOG = LoggerFactory.getLogger(WebResourceController.class);

    private final ResourceService resourceService;

    /**
     * 下面如果是多参可以写多个@Qualifier
     * 其中Qualifier是可选的
     *
     * @author quentin
     * @date 2022/11/2
     */
    public WebResourceController(
            ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 资源页面
     *
     * @author quentin
     * @date 2022/11/2
     */
    @GetMapping("")
    public String resourcePage() {
        return "resource";
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

    @PostMapping("/add")
    public ResponseEntity<String> addResource(
            @RequestBody WebResource resource) {
        boolean isAdded = false;
        try {
            isAdded = resourceService.addResource(resource);
        } catch (Exception e) {
            LOG.info("error===");
            e.printStackTrace();
        }
        if (isAdded) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
    }

    // TODO 待完善更新资源路径功能
}
