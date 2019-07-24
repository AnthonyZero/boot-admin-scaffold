package com.anthonyzero.scaffold.custom.service.impl;

import com.anthonyzero.scaffold.custom.entity.Custom;
import com.anthonyzero.scaffold.custom.mapper.CustomMapper;
import com.anthonyzero.scaffold.custom.service.CustomService;
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

}
