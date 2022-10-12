/**
 * @author:quentin
 * @create: 2022-10-10 20:47
 * @Description:
 */
package org.quentin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelComeController {
    // 此处不能再加/ 如果加了访问就是 /welcome/
    @GetMapping("")
    public String welcome(){
        return "welcome";
    }
}
