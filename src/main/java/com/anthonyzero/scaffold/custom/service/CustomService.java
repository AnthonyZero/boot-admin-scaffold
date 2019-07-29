package com.anthonyzero.scaffold.custom.service;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.custom.dto.CustomInfo;
import com.anthonyzero.scaffold.custom.entity.Custom;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-24
 */
public interface CustomService extends IService<Custom> {

    /**
     * 分页查询客户信息
     * @param customInfo
     * @param request
     * @return
     */
    IPage<CustomInfo> pageCustom(CustomInfo customInfo, RequestQuery request);
}
