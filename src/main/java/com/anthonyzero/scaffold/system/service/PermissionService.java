package com.anthonyzero.scaffold.system.service;

import com.anthonyzero.scaffold.common.core.MenuTree;
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

    /**
     * 查找用户菜单集合 不含按钮
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    MenuTree<Permission> findUserMenus(String username);


    /**
     * 查找所有的菜单/按钮 （树形结构）
     * @param menuName
     * @return
     */
    MenuTree<Permission> findMenus(String menuName);

    /**
     * 删除权限 权限及子权限 用户角色权限及子权限
     * @param permissionIds
     */
    void deletePermissions(String permissionIds);
}
