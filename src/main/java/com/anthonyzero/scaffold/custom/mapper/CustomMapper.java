package com.anthonyzero.scaffold.custom.mapper;

import com.anthonyzero.scaffold.custom.dto.CustomInfo;
import com.anthonyzero.scaffold.custom.entity.Custom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-24
 */
public interface CustomMapper extends BaseMapper<Custom> {

    /**
     * 分页查询客户信息
     * @param page
     * @param customInfo
     * @return
     */
    IPage<CustomInfo> pageCustom(Page page, @Param("custom") CustomInfo customInfo);
}
