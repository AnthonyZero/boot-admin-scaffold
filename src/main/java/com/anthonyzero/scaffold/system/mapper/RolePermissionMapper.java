package com.anthonyzero.scaffold.system.mapper;

import com.anthonyzero.scaffold.system.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {


    /**
     * 递归删除用户角色 权限
     *
     * @param permissionId permissionId 权限ID
     */
    void deleteRolePermissions(String permissionId);
}
