package org.quentin.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.quentin.web.shiro.MyNamePassToken;
import org.quentin.web.dto.UserAccount;
import org.quentin.web.pojo.RetMessage;
import org.quentin.web.service.AccountService;
import org.quentin.web.validator.UserAccValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    private final AccountService accService;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthController.class);

    public AuthController(
            AccountService accountService) {
        this.accService = accountService;
    }

    // 此注册只在当前Controller可用
    @InitBinder
    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(userAccValidator);
        binder.addValidators(new UserAccValidator());
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        // request.getParameter 获取前端url显式参数
//        String id = request.getParameter("id");
        return "login";
    }

    /**
     * TODO 实现remember me
     * todo 考虑几种情况 用户名密码空时 处理一下 ,有用户名没密码 也要一样处理 最关键的是两个都提供 思路捋一下
     * <p>可以直接返回对象并转换为json或其他类型 搭配jackson-databind使用
     * <p>参数验证有两种方法：
     * - 使用javax.validation.Valid 使用要进行数据验证 需要使用@Valid注解形参 and Validated对象(作为形参)
     * 例如： login(@RequestBody @Valid UserAccount account)
     * - 使用spring的@Validated 配合BindingResult （还没试）
     * <p>HttpEntity与@RequestBody类似 作为形参使用
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<RetMessage<String>> login(
            @RequestBody UserAccount account) {
        if (account.getUsername() != null &&
                accService.existAccount(account.getUsername())) {
            UsernamePasswordToken token = new MyNamePassToken(account);
            try {
                SecurityUtils.getSubject().login(token);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RetMessage<String>().status("ok").msg("登录成功"));
            } catch (UnknownAccountException uae) {
                LOGGER.info("用户不存在");
            } catch (IncorrectCredentialsException ice) {
                LOGGER.info("密码不匹配!!!");
            } catch (LockedAccountException lae) {
                LOGGER.info(lae.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RetMessage<String>().status("error").msg(
                                "account for this username is locked - can't login."));
            } catch (AuthenticationException ae) {
                LOGGER.info("未知错误");
                LOGGER.error(ae.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RetMessage<String>().status("error").msg("" +
                                "unknown error! please contact admin"));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new RetMessage<String>().status("error").msg(
                        "username or password not correct"));
    }

    @PostMapping("/logout")
    public ResponseEntity<RetMessage<String>> logOut() {
        // 通过前端cookie中的JSESSIONID确定当前登录对象进行退出
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().eTag("returnMsg").body(
                new RetMessage<String>().msg("ok"));
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    //其中Method validation relies on AOP Proxies around the target classes
    public ResponseEntity<RetMessage<String>> register(
            @Validated @RequestBody UserAccount account, BindingResult result) {
        if (!result.hasErrors() && !accService.existAccount(account.getUsername())) {
            if (accService.registerAccount(account)) {
                return ResponseEntity.ok().eTag("returnMsg").body(
                        new RetMessage<String>().msg("ok"));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).eTag("error").body(
                new RetMessage<String>().msg("username invalid"));
    }

    @GetMapping("/get-mes")
    public ResponseEntity<String> getMes() {
        return ResponseEntity.ok().eTag("etag").body("hello");
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
