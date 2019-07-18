package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.system.entity.UserRole;
import com.anthonyzero.scaffold.system.mapper.UserRoleMapper;
import com.anthonyzero.scaffold.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


    @Override
    @Transactional
    public void deleteUserRolesByUserId(List<String> userIds) {
        baseMapper.delete(new LambdaQueryWrapper<UserRole>().in(UserRole::getUserId, userIds));
    }
}
