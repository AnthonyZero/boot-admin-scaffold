package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.annotation.SysLog;
import com.anthonyzero.scaffold.common.authentication.ShiroRealm;
import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.MenuTree;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.GlobalException;
import com.anthonyzero.scaffold.system.entity.Permission;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ShiroRealm shiroRealm;

    /**
     * 获取自己的菜单树 不含按钮
     * @param username
     * @return
     */
    @GetMapping("{username}")
    public Response getUserMenus(@PathVariable String username) {
        User currentUser = getCurrentUser();
        if (!StringUtils.equalsIgnoreCase(username, currentUser.getUsername()))
            throw new GlobalException(CodeMsgEnum.REQUEST_ILLEGAL);
        MenuTree<Permission> userMenus = permissionService.findUserMenus(username);
        return Response.success(userMenus);
    }

    /**
     * 获取整个菜单树 包含按钮
     * @param menuName 菜单名称查询条件
     * @return
     */
    @GetMapping("tree")
    public Response getMenusTree(String menuName) {
        MenuTree<Permission> menus = permissionService.findMenus(menuName);
        return Response.success(menus.getChilds()); //数组 从第一级开始
    }


    /**
     * 添加权限
     * @param permission
     * @return
     */
    @SysLog("新增菜单/按钮")
    @PostMapping("add")
    @RequiresPermissions("menu:add")
    public Response addPermission(Permission permission) {
        permission.setCreateTime(LocalDateTime.now());
        if (permission.getParentId() == null)
            permission.setParentId(0L);
        if (SysConstant.TYPE_BUTTON.equals(permission.getType())) {
            permission.setUrl(null);
            permission.setIcon(null);
        }
        permissionService.save(permission);
        return Response.success();
    }


    /**
     * 删除权限
     * @param permissionIds
     * @return
     */
    @SysLog("删除菜单/按钮")
    @GetMapping("delete/{permissionIds}")
    @RequiresPermissions("menu:delete")
    public Response deleteMenu(@PathVariable String permissionIds) {
        permissionService.deletePermissions(permissionIds);
        return Response.success();
    }


    /**
     * 修改菜单/按钮
     * @param permission
     * @return
     */
    @SysLog("修改菜单/按钮")
    @PostMapping("update")
    @RequiresPermissions("menu:update")
    public Response updateMenu(Permission permission) {
        permission.setModifyTime(LocalDateTime.now());
        if (permission.getParentId() == null)
            permission.setParentId(0L);
        if (SysConstant.TYPE_BUTTON.equals(permission.getType())) {
            permission.setUrl(null);
            permission.setIcon(null);
        }
        permissionService.updateById(permission);
        shiroRealm.clearCache();
        return Response.success();
    }
}
