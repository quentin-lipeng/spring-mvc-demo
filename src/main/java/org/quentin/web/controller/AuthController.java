package org.quentin.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.quentin.web.service.impl.AccountServiceImpl;
import org.quentin.web.user.pojo.Account;
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
    @Resource(type = AccountServiceImpl.class)
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
        if (account.getUsername() != null) {
            Account accByAccName = accService.getAccByAccName(account.getUsername());
            logger.info("accByAccName = " + accByAccName.getUsername());

            UsernamePasswordToken token = new UsernamePasswordToken(account.getUsername(), account.getPassword());
            try {
                SecurityUtils.getSubject().login(token);
                return ResponseEntity.ok().eTag("returnMsg").body(new RetMessage().msg("ok"));
            } catch (UnknownAccountException uae) {
                //username wasn't in the system, show them an error message?
                logger.error(uae.getMessage());
                throw uae;
            } catch (IncorrectCredentialsException ice) {
                //password didn't match, try again?
                logger.error("密码不匹配!!!");
                return ResponseEntity.status(400).eTag("error").body(new RetMessage().msg("password not correct"));
            } catch (LockedAccountException lae) {
                logger.error(lae.getMessage());
                //account for that username is locked - can't login.  Show them a message?
            } catch (AuthenticationException ae) {
                logger.error(ae.getMessage());
            }
        }
        return ResponseEntity.status(400).eTag("error").body(new RetMessage().status("error"));
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
