/**
 * @author:quentin
 * @create: 2022-10-10 20:57
 * @Description:
 */
package org.quentin.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.quentin.web.pojo.RetMessage;
import org.quentin.web.pojo.WebResource;
import org.quentin.web.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
// TODO 路径是否加上/ 斜杠
@RequestMapping("/resource")
public class WebResourceController {
    public static final Logger LOG = LoggerFactory.getLogger(WebResourceController.class);

    @Resource
    private ResourceService resourceService;

    @GetMapping("")
    public String resourcePage() {
        return "resource";
    }

    @GetMapping("/list")
    public ResponseEntity<RetMessage<ArrayList<WebResource>>> getResource() {
        ArrayList<WebResource> resources = resourceService.resourceList();

        return ResponseEntity.status(HttpStatus.OK).body(
                new RetMessage<ArrayList<WebResource>>().data(resources));
    }

}
