package com.anthonyzero.scaffold.common.aspect;

import com.anthonyzero.scaffold.common.utils.HttpContextUtil;
import com.anthonyzero.scaffold.common.utils.IPUtil;
import com.anthonyzero.scaffold.system.entity.SysLog;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 记录用户操作日志
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;


    @Pointcut("@annotation(com.anthonyzero.scaffold.common.annotation.SysLog)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置 IP地址
        String ip = IPUtil.getIpAddress(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SysLog log = new SysLog();
        if (user != null)
            log.setUsername(user.getUsername());
        log.setIp(ip);
        log.setTime(time);
        sysLogService.saveLog(point, log);
        return result;
    }
}
