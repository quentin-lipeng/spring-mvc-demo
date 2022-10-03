/**
 * @author:quentin
 * @create: 2022-10-02 23:26
 * @Description: index
 */
package org.quentin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class IndexController {
    @GetMapping("/home")
    public String index() {
        return "index";
    }
}
