package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.MenuTree;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.GlobalException;
import com.anthonyzero.scaffold.system.entity.Permission;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取自己的菜单树
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
}
