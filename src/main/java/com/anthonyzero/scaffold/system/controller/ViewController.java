package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.utils.CaptchaUtil;
import com.wf.captcha.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

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
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_NUM_AND_UPPER, request, response);
        System.out.println(code);
    }
}
