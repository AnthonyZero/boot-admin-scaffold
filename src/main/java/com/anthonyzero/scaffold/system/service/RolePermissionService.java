package com.anthonyzero.scaffold.system.service;

import com.anthonyzero.scaffold.system.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 通过角色 id 删除 角色权限
     *
     * @param roleIds 角色 id
     */
    void deleteRolePermsByRoleId(List<String> roleIds);
}
