package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.utils.HttpContextUtil;
import com.anthonyzero.scaffold.common.utils.IPUtil;
import com.anthonyzero.scaffold.system.entity.LoginLog;
import com.anthonyzero.scaffold.system.mapper.LoginLogMapper;
import com.anthonyzero.scaffold.system.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-10
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    @Transactional
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(LocalDateTime.now());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddress(request);
        loginLog.setIp(ip);
        this.baseMapper.insert(loginLog);
    }
}
