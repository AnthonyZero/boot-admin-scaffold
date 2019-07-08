package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.system.entity.Role;
import com.anthonyzero.scaffold.system.mapper.RoleMapper;
import com.anthonyzero.scaffold.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> findUserRole(String username) {
        return this.baseMapper.findUserRole(username);
    }
}
