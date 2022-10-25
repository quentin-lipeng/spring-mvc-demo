
package org.quentin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:quentin
 * @create: 2022-10-10 20:47
 * @Description:
 */
@Controller
@RequestMapping("/welcome")
public class WelComeController {
    /**
     * 此处不能再加 "/" 如果加了之后访问地址就是 "/welcome/"
     * @author quentin
     * @date 2022/10/25
     */
    @GetMapping("")
    public String welcome(){
        return "welcome";
    }
}
