package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.annotation.SysLog;
import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.system.entity.Role;
import com.anthonyzero.scaffold.system.service.RoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     * @param role
     * @return
     */
    @GetMapping
    public Response getAllRoles(Role role) {
        return Response.success(roleService.findRoles(role));
    }


    /**
     * 角色列表分页
     * @param role
     * @param query
     * @return
     */
    @GetMapping("list")
    @RequiresPermissions("role:view")
    public Response roleList(Role role, RequestQuery query) {
        IPage<Role> roleIPage = roleService.pageRoles(role, query);
        Map<String, Object> dataTable = getDataTable(roleIPage);
        return Response.success(dataTable);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @SysLog("新增角色")
    @PostMapping("add")
    @RequiresPermissions("role:add")
    public Response createRole(Role role) {
        roleService.createRole(role);
        return Response.success();
    }


    /**
     * 修改角色
     * @param role
     * @return
     */
    @SysLog("修改角色")
    @PostMapping("update")
    @RequiresPermissions("role:update")
    public Response updateRole(Role role) {
        roleService.updateRole(role);
        return Response.success();
    }


    /**
     * 删除角色
     * @param roleIds
     * @return
     */
    @SysLog("删除角色")
    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    public Response deleteRoles(@PathVariable String roleIds) {
        if (StringUtils.isBlank(roleIds)) {
            return Response.error(CodeMsgEnum.PARAMETER_ERROR);
        }
        roleService.deleteRoles(roleIds);
        return Response.success();
    }
}
