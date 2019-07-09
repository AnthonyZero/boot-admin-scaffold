package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.RedisConnectException;
import com.anthonyzero.scaffold.common.utils.MD5Util;
import com.anthonyzero.scaffold.system.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController extends BaseController {

    @Autowired
    private RedisService redisService;

    /**
     * 登录
     * @param username
     * @param password
     * @param verifyCode
     * @param rememberMe
     * @param request
     * @return
     * @throws RedisConnectException
     */
    @PostMapping("/login")
    public Response login(String username, String password, String verifyCode, boolean rememberMe, HttpServletRequest request) throws RedisConnectException {
        HttpSession session = request.getSession();
        String key = SysConstant.CODE_PREFIX + session.getId();
        String sessionCode = redisService.get(key);
        if (!StringUtils.equalsIgnoreCase(sessionCode, verifyCode)) {
            return Response.error(CodeMsgEnum.VERIFYCODE_ERROR);
        }
        password = MD5Util.encrypt(password, username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        super.login(token);
        return Response.success();
    }
}
