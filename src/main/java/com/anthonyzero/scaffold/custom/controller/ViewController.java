package com.anthonyzero.scaffold.custom.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.SysConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController extends BaseController {

    /**
     * 客户管理页面
     * @return
     */
    @RequestMapping(SysConstant.VIEW_PREFIX + "custom/user")
    public String pageCustomIndex() {
        return SysConstant.VIEW_PREFIX + "custom/user/list";
    }
}
