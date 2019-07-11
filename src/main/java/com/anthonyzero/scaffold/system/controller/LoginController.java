package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.RedisConnectException;
import com.anthonyzero.scaffold.common.utils.MD5Util;
import com.anthonyzero.scaffold.system.entity.LoginLog;
import com.anthonyzero.scaffold.system.service.LoginLogService;
import com.anthonyzero.scaffold.system.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController extends BaseController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginLogService loginLogService;

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
        //保存登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        loginLog.setupOsBrowserInfo(); //设置os browser
        loginLogService.saveLoginLog(loginLog);
        return Response.success();
    }

    /**
     * 获取首页 部分数据
     * @param username
     * @return
     */
    @GetMapping("index/{username}")
    public Response index(@PathVariable String username) {
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        Long totalVisitCount = loginLogService.getTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = loginLogService.getTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        Long todayIpCount = loginLogService.getTodayIpCount();
        data.put("todayIp", todayIpCount);
        // 获取近期十天系统访问记录
        List<Map<String, Object>> lastTenVisitCount = loginLogService.getLastDaysVisitCount(null, 10);
        data.put("lastTenVisitCount", lastTenVisitCount);
        List<Map<String, Object>> lastTenUserVisitCount = loginLogService.getLastDaysVisitCount(username, 10);
        data.put("lastTenUserVisitCount", lastTenUserVisitCount);
        return Response.success(data);
    }
}
