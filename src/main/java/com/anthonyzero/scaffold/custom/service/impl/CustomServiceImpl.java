package com.anthonyzero.scaffold.custom.service.impl;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.utils.SortUtil;
import com.anthonyzero.scaffold.custom.dto.CustomInfo;
import com.anthonyzero.scaffold.custom.entity.Custom;
import com.anthonyzero.scaffold.custom.entity.DemandType;
import com.anthonyzero.scaffold.custom.mapper.CustomMapper;
import com.anthonyzero.scaffold.custom.mapper.DemandTypeMapper;
import com.anthonyzero.scaffold.custom.service.CustomService;
import com.anthonyzero.scaffold.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

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

    @Autowired
    private DemandTypeMapper demandTypeMapper;

    @Override
    public IPage<CustomInfo> pageCustom(CustomInfo customInfo, RequestQuery request) {
        Page<CustomInfo> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", SysConstant.ORDER_DESC);
        return baseMapper.pageCustom(page, customInfo);
    }

    @Override
    @Transactional
    public void createCustom(Custom custom) {
        String[] demand = custom.getDemandTypeStr().split(",");
        User current = (User) SecurityUtils.getSubject().getPrincipal();
        custom.setCreateUserId(current.getUserId());
        custom.setCreateTime(LocalDateTime.now());
        baseMapper.insert(custom);
        Arrays.asList(demand).forEach(demandType -> {
            DemandType type = new DemandType();
            type.setCustomId(custom.getCustomId());
            type.setDemandType(Integer.valueOf(demandType));
            type.setDemandTypeName(DemandType.demantTypeMap.get(type.getDemandType()));
            demandTypeMapper.insert(type);
        });
    }

    @Override
    @Transactional
    public void updateCustom(Custom custom) {
        String[] demand = custom.getDemandTypeStr().split(",");
        User current = (User) SecurityUtils.getSubject().getPrincipal();
        demandTypeMapper.delete(new LambdaQueryWrapper<DemandType>().eq(DemandType::getCustomId, custom.getCustomId()));
        custom.setModifyTime(LocalDateTime.now());
        custom.setModifyUserId(current.getUserId());
        baseMapper.updateById(custom);
        Arrays.asList(demand).forEach(demandType -> {
            DemandType type = new DemandType();
            type.setCustomId(custom.getCustomId());
            type.setDemandType(Integer.valueOf(demandType));
            type.setDemandTypeName(DemandType.demantTypeMap.get(type.getDemandType()));
            demandTypeMapper.insert(type);
        });
    }
}
