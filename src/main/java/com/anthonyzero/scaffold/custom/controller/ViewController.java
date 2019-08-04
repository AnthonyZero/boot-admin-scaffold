package com.anthonyzero.scaffold.custom.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.custom.entity.Custom;
import com.anthonyzero.scaffold.custom.entity.DemandType;
import com.anthonyzero.scaffold.custom.service.CustomService;
import com.anthonyzero.scaffold.custom.service.DemandTypeService;
import com.anthonyzero.scaffold.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller("customView")
public class ViewController extends BaseController {

    @Autowired
    private CustomService customService;
    @Autowired
    private DemandTypeService demandTypeService;
    @Autowired
    private UserService userService;

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


    /**
     * 客户详情页面
     * @param customId
     * @param modelMap
     * @return
     */
    @RequestMapping(SysConstant.VIEW_PREFIX + "custom/detail/{customId}")
    @RequiresPermissions("custom:view")
    public String customDetailPage(@PathVariable Long customId, ModelMap modelMap) {
        Custom custom = customService.getById(customId);
        List<DemandType> list = demandTypeService.list(new LambdaQueryWrapper<DemandType>().eq(DemandType::getCustomId, customId));
        modelMap.addAttribute("custom", custom);
        modelMap.addAttribute("demandTypeList", list.stream().map(DemandType::getDemandType).collect(Collectors.toList()));
        modelMap.addAttribute("saleUserName", userService.getById(custom.getSaleUserId()).getNickname());
        modelMap.addAttribute("receiverName", userService.getById(custom.getReceiver()).getNickname());
        return SysConstant.VIEW_PREFIX + "custom/user/customDetail";
    }


    /**
     * 修改客户信息页面
     * @param customId
     * @param modelMap
     * @return
     */
    @RequestMapping(SysConstant.VIEW_PREFIX + "custom/update/{customId}")
    @RequiresPermissions("custom:update")
    public String customUpdatePage(@PathVariable Long customId, ModelMap modelMap) {
        Custom custom = customService.getById(customId);
        List<DemandType> list = demandTypeService.list(new LambdaQueryWrapper<DemandType>().eq(DemandType::getCustomId, customId));
        modelMap.addAttribute("custom", custom);
        modelMap.addAttribute("demandTypeList", list.stream().map(DemandType::getDemandType).collect(Collectors.toList()));
        return SysConstant.VIEW_PREFIX + "custom/user/customUpdate";
    }
}
