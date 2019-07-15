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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
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
        if(request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            //ajax请求 session失效或过期的时候会发生 ->会跳转配置的登录页  通过控制抛出异常让前端resolveResponse捕获完成跳转
            throw new ExpiredSessionException();
        } else {
            return "login";
        }
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


    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentUserAuthorizationInfo();
        User user = userService.findByName(getCurrentUser().getUsername()); //获取实时用户信息
        user.setPassword("It's a secret");
        model.addAttribute("user", user);
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles",authorizationInfo.getRoles());
        return "index";
    }


    /**
     * 首页布局
     * @return
     */
    @GetMapping(SysConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return SysConstant.VIEW_PREFIX + "layout";
    }

    /**
     * 首页中的 主体部分
     * @return
     */
    @RequestMapping(SysConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return SysConstant.VIEW_PREFIX + "index";
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "404")
    public String error404() {
        return  SysConstant.VIEW_PREFIX + "error/404";
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "403")
    public String error403() {
        return  SysConstant.VIEW_PREFIX + "error/403";
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "500")
    public String error500() {
        return  SysConstant.VIEW_PREFIX + "error/500";
    }

    /**
     * 个人中心页面
     * @return
     */
    @GetMapping(SysConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return SysConstant.VIEW_PREFIX + "system/user/userProfile";
    }

    /**
     * 头像选择页面
     * @return
     */
    @GetMapping(SysConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return SysConstant.VIEW_PREFIX + "system/user/avatar";
    }

    /**
     * 个人信息修改页面
     * @return
     */
    @GetMapping(SysConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return SysConstant.VIEW_PREFIX + "system/user/profileUpdate";
    }


    /**
     * 密码修改页面
     * @return
     */
    @GetMapping(SysConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return SysConstant.VIEW_PREFIX + "system/user/passwordUpdate";
    }


    /**
     * 菜单管理页面
     * @return
     */
    @GetMapping(SysConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return SysConstant.VIEW_PREFIX + "system/menu/menu";
    }


    /**
     * 选择菜单图标icon页面
     * @return
     */
    @GetMapping(SysConstant.VIEW_PREFIX + "other/icon")
    public String menusIcon() {
        return SysConstant.VIEW_PREFIX + "system/menu/icon";
    }

}
