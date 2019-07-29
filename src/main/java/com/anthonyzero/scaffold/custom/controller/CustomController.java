package com.anthonyzero.scaffold.custom.controller;


import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.custom.dto.CustomInfo;
import com.anthonyzero.scaffold.custom.service.CustomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
