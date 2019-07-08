package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.mapper.UserMapper;
import com.anthonyzero.scaffold.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByName(String username) {
        return this.baseMapper.findByName(username);
    }
}
