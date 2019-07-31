package com.anthonyzero.scaffold.custom.controller;


import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.custom.dto.CustomInfo;
import com.anthonyzero.scaffold.custom.entity.Custom;
import com.anthonyzero.scaffold.custom.service.CustomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("custom")
public class CustomController extends BaseController {

    @Autowired
    private CustomService customService;

    /**
     * 客户信息分页
     * @param customInfo
     * @param request
     * @return
     */
    @GetMapping("page")
    @RequiresPermissions("custom:view")
    public Response customList(CustomInfo customInfo, RequestQuery request) {
        Map<String, Object> dataTable = getDataTable(customService.pageCustom(customInfo, request));
        return Response.success(dataTable);
    }

    /**
     * 添加客户
     * @param custom
     * @return
     */
    @PostMapping("add")
    @RequiresPermissions("custom:add")
    public Response addCustom(Custom custom) {
        if (custom.getSaleUserId() == null) {
            custom.setSaleUserId(getUserId());
        }
        if (StringUtils.isBlank(custom.getFullname()) || StringUtils.isBlank(custom.getIdCard()) || StringUtils.isBlank(custom.getDemandTypeStr())) {
            return Response.error(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        customService.createCustom(custom);
        return Response.success();
    }
}
