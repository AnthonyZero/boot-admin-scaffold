package com.anthonyzero.scaffold.common.runner;

import com.anthonyzero.scaffold.common.exception.RedisConnectException;
import com.anthonyzero.scaffold.common.properties.ShiroProperties;
import com.anthonyzero.scaffold.system.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * springboot启动 加载验证
 */
@Slf4j
@Component
public class AdminStartedUpRunner implements ApplicationRunner{

    @Autowired
    private ConfigurableApplicationContext context;
    @Autowired
    private ShiroProperties shiroProperties;
    @Autowired
    private RedisService redisService;
    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            redisService.exists("root");
        } catch (Exception e) {
            log.error("Admin启动失败，{}", e.getMessage());
            log.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            context.close();
        }
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = String.format("http://%s:%s", address.getHostAddress(), port);
            String loginUrl = shiroProperties.getLoginUrl();
            if (StringUtils.isNotBlank(contextPath))
                url += contextPath;
            if (StringUtils.isNotBlank(loginUrl))
                url += loginUrl;
            log.info("Admin权限系统启动完毕，地址：{}", url);
        }
    }
}
