package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.exception.RedisConnectException;
import com.anthonyzero.scaffold.common.utils.CaptchaUtil;
import com.anthonyzero.scaffold.system.service.RedisService;
import com.wf.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ViewController {

    @Autowired
    private RedisService redisService;

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
        HttpSession session = request.getSession();
        String code = CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_NUM_AND_UPPER, request, response);
        String key = SysConstant.CODE_PREFIX + session.getId();
        redisService.set(key, code, 120);
    }
}
