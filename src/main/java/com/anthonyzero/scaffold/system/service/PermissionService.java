package com.anthonyzero.scaffold.system.service;

import com.anthonyzero.scaffold.system.entity.Permission;
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
public interface PermissionService extends IService<Permission> {

    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Permission> findUserPermissions(String username);
}
