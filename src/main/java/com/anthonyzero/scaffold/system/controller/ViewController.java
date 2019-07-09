package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.authentication.ShiroHelper;
import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.exception.RedisConnectException;
import com.anthonyzero.scaffold.common.utils.CaptchaUtil;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.service.RedisService;
import com.anthonyzero.scaffold.system.service.UserService;
import com.wf.captcha.Captcha;
import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ViewController extends BaseController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private ShiroHelper shiroHelper;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    /**
     * 验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, RedisConnectException {
        redisService.exists("root");//测试连接
        HttpSession session = request.getSession();
        String code = CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_NUM_AND_UPPER, request, response);
        String key = SysConstant.CODE_PREFIX + session.getId();
        redisService.set(key, code, 120);
    }


    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentUserAuthorizationInfo();
        User user = super.getCurrentUser();
        user.setPassword("It's a secret");
        model.addAttribute("user", userService.findByName(user.getUsername())); // 获取实时的用户信息
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles",authorizationInfo.getRoles());
        return "index";
    }


    @GetMapping(SysConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return "views/layout";
    }

    @RequestMapping(SysConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return "views/index";
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "404")
    public String error404() {
        return  "views/error/404";
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "403")
    public String error403() {
        return  "views/error/403";
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "500")
    public String error500() {
        return  "views/error/500";
    }

}
