package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.system.entity.LoginLog;
import com.anthonyzero.scaffold.system.mapper.LoginLogMapper;
import com.anthonyzero.scaffold.system.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
