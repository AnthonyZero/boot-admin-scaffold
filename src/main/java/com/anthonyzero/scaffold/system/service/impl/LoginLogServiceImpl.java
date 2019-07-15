package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.utils.HttpContextUtil;
import com.anthonyzero.scaffold.common.utils.IPUtil;
import com.anthonyzero.scaffold.system.entity.LoginLog;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.mapper.LoginLogMapper;
import com.anthonyzero.scaffold.system.mapper.UserMapper;
import com.anthonyzero.scaffold.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(LocalDateTime.now());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddress(request);
        loginLog.setIp(ip);
        this.baseMapper.insert(loginLog);
        //更新登录时间
        User user = new User();
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, loginLog.getUsername()));
    }

    @Override
    public Long getTotalVisitCount() {
        return baseMapper.getTotalVisitCount();
    }

    @Override
    public Long getTodayVisitCount() {
        return baseMapper.getTodayVisitCount();
    }

    @Override
    public Long getTodayIpCount() {
        return baseMapper.getTodayIpCount();
    }

    @Override
    public List<Map<String, Object>> getLastDaysVisitCount(String username) {
        return baseMapper.getLastDaysVisitCount(username);
    }


}
