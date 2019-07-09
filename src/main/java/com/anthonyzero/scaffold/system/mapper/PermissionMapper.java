package com.anthonyzero.scaffold.system.mapper;

import com.anthonyzero.scaffold.system.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Permission> findUserPermissions(String username);


    /**
     * 查找用户的菜单列表
     * @param username
     * @return
     */
    List<Permission> findUserMenus(String username);
}
