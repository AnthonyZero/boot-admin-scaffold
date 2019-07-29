package com.anthonyzero.scaffold.custom.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.SysConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("customView")
public class ViewController extends BaseController {

    /**
     * 客户管理页面
     * @return
     */
    @RequestMapping(SysConstant.VIEW_PREFIX + "custom/user")
    @RequiresPermissions("custom:view")
    public String pageCustomIndex() {
        return SysConstant.VIEW_PREFIX + "custom/user/list";
    }


    /**
     * 客户信息添加页面
     * @return
     */
    @RequestMapping(SysConstant.VIEW_PREFIX + "custom/add")
    @RequiresPermissions("custom:add")
    public String addCustomIndex() {
        return SysConstant.VIEW_PREFIX + "custom/user/customAdd";
    }
}
