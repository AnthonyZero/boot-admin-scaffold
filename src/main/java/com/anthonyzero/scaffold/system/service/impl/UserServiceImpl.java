package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.utils.SortUtil;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.mapper.UserMapper;
import com.anthonyzero.scaffold.system.service.UserService;
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
 * @since 2019-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByName(String username) {
        return this.baseMapper.findByName(username);
    }

    @Override
    public IPage<User> pageUser(User user, RequestQuery request) {
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize()); //那一页 每页多少
        SortUtil.handlePageSort(request, page, "createTime", SysConstant.ORDER_DESC); //按照前台的排序字段排序，如果没有按默认排序规则
        return baseMapper.selectPageUser(page, user);
    }
}
