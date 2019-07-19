package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.utils.SortUtil;
import com.anthonyzero.scaffold.system.entity.Role;
import com.anthonyzero.scaffold.system.entity.RolePermission;
import com.anthonyzero.scaffold.system.mapper.RoleMapper;
import com.anthonyzero.scaffold.system.service.RolePermissionService;
import com.anthonyzero.scaffold.system.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Role> findUserRole(String username) {
        return this.baseMapper.findUserRole(username);
    }

    @Override
    public List<Role> findRoles(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(role.getName()))
            queryWrapper.lambda().like(Role::getName, role.getName());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Role> pageRoles(Role role, RequestQuery query) {
        Page<Role> page = new Page<>(query.getPageNum(), query.getPageSize());
        SortUtil.handlePageSort(query, page, "createTime", SysConstant.ORDER_DESC);
        return baseMapper.pageRoles(page, role);
    }

    @Override
    @Transactional
    public void createRole(Role role) {
        role.setCreateTime(LocalDateTime.now());
        baseMapper.insert(role);
        saveRolePermissions(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setModifyTime(LocalDateTime.now());
        updateById(role);
        List<String> roleIdList = new ArrayList<>();
        roleIdList.add(String.valueOf(role.getRoleId()));
        rolePermissionService.deleteRolePermsByRoleId(roleIdList); //通过角色ID删除 该角色权限
        saveRolePermissions(role); //重新保存新角色权限
    }


    private void saveRolePermissions(Role role) {
        if (StringUtils.isNotBlank(role.getPermissionIds())) {
            String[] permissionIds = role.getPermissionIds().split(StringPool.COMMA);
            List<RolePermission> rolePermissions = new ArrayList<>();
            Arrays.stream(permissionIds).forEach(permissionId -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermissionId(Long.valueOf(permissionId));
                rolePermission.setRoleId(role.getRoleId());
                rolePermissions.add(rolePermission);
            });
            rolePermissionService.saveBatch(rolePermissions);
        }
    }
}
