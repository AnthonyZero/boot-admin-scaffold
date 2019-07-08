package com.anthonyzero.scaffold.system.mapper;

import com.anthonyzero.scaffold.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过账号获取用户信息
     * @param username
     * @return
     */
    User findByName(String username);
}
