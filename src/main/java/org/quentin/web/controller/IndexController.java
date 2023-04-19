package org.quentin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:quentin
 * @create: 2022-10-02 23:26
 * @Description: index
 */
@RequestMapping("/")
@Controller
public class IndexController {

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * redirect 用于url之间的重定向 前端会从一个url跳转到另一个url
     * forward 用于在服务端从一个servlet到另一个 前端不会有url的变化
     * 写此映射方法是为了使用redirect
     */
    @GetMapping("")
    public String welcome() {
        return "redirect:/welcome";
    }

    @GetMapping("/home")
    public String index() {
        return "index";
    }

}
