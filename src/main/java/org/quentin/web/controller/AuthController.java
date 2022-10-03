package org.quentin.web.controller;

import org.quentin.web.pojo.Account;
import org.quentin.web.pojo.RetMessage;
import org.quentin.web.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:quentin
 * @create: 2022-09-30 17:36
 * @Description: index
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    /**
     * 也可以使用@Resource(type = AccountServiceImpl.class)
     */
    @Resource
    private AccountService accService;

    public static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        // request.getParameter 获取前端url显式参数
//        String id = request.getParameter("id");
//        Account accByAccId = accService.getAccByAccId(id);
//        logger.info("account = " + accByAccId);
        return "login";
    }

    @PostMapping("/login")
    // 可以直接返回对象并转换为json或其他类型 搭配jackson-databind使用
    @ResponseBody
    public ResponseEntity<RetMessage> login(@RequestBody Account account) {
        if (account.getUsername() != null){
            Account newAccount = accService.getAccByAccName(account.getUsername());
        }
//        return new RetMessage().status("ok").msg("hello");
        return ResponseEntity.ok().eTag("returnMsg").body(new RetMessage().status("ok"));
    }

    @PostMapping("/get-mes")
    public ResponseEntity<String> getMes() {
        return ResponseEntity.ok().eTag("etag ").body("hello");
    }

    //    @GetMapping("/acc-id")
    // 如果没有配置name属性 默认为add的对象类型名称 如addAttribute(new Account());
    // 获得其对象就需要使用 model.getAttribute("account")
    // 在请求此controller的RequestMapping配置的路径下会先运行此方法
    // 也可以返回Account对象 和addAttribute功能一致
    // 也可以配合RequestMapping或其他路径映射注解一起使用
//    @ModelAttribute
//    public void getAccId(@RequestParam String id, Model model) {
//        logger.info("req param = " + id);
//        Account account = new Account();
//        account.setAccountId(id);
//        model.addAttribute(account);
//    }
}
