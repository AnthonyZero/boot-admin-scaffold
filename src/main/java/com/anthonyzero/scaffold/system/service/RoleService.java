package com.anthonyzero.scaffold.system.service;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.system.entity.Role;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public interface RoleService extends IService<Role> {

    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<Role> findUserRole(String username);

    /**
     * 查找所有角色
     * @param role
     * @return
     */
    List<Role> findRoles(Role role);

    /**
     * 查找所有角色（分页）
     *
     * @param role    角色对象（用于传递查询条件）
     * @param query query
     * @return IPage
     */
    IPage<Role> pageRoles(Role role, RequestQuery query);


    /**
     * 新增角色
     * @param role
     */
    void createRole(Role role);


    /**
     * 修改角色
     *
     * @param role 待修改的角色
     */
    void updateRole(Role role);


    /**
     * 删除角色
     *
     * @param roleIds 待删除角色的 id集合
     */
    void deleteRoles(String roleIds);
}
