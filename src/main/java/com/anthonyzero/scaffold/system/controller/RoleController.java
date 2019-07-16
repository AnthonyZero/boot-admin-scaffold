package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.system.entity.Role;
import com.anthonyzero.scaffold.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
