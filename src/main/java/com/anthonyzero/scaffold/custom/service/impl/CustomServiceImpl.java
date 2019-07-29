package com.anthonyzero.scaffold.custom.service.impl;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.utils.SortUtil;
import com.anthonyzero.scaffold.custom.dto.CustomInfo;
import com.anthonyzero.scaffold.custom.entity.Custom;
import com.anthonyzero.scaffold.custom.mapper.CustomMapper;
import com.anthonyzero.scaffold.custom.service.CustomService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-24
 */
@Service
public class CustomServiceImpl extends ServiceImpl<CustomMapper, Custom> implements CustomService {

    @Override
    public IPage<CustomInfo> pageCustom(CustomInfo customInfo, RequestQuery request) {
        Page<CustomInfo> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", SysConstant.ORDER_DESC);
        return baseMapper.pageCustom(page, customInfo);
    }
}
