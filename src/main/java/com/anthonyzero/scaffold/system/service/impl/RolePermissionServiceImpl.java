package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.system.entity.RolePermission;
import com.anthonyzero.scaffold.system.mapper.RolePermissionMapper;
import com.anthonyzero.scaffold.system.service.RolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    @Transactional
    public void deleteRolePermsByRoleId(List<String> roleIds) {
        baseMapper.delete(new LambdaQueryWrapper<RolePermission>().in(RolePermission::getRoleId, roleIds));
    }

    @Override
    @Transactional
    public void deleteRolePermsByPermId(List<String> permIds) {
        baseMapper.delete(new QueryWrapper<RolePermission>().lambda().in(RolePermission::getPermissionId, permIds));
    }
}
