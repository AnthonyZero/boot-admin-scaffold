package com.anthonyzero.scaffold.system.service;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.system.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByName(String username);

    /**
     * 用户列表分页查询
     * @param user
     * @param request
     * @return
     */
    IPage<User> pageUser(User user, RequestQuery request);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);
}
