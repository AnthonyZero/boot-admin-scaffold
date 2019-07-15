package com.anthonyzero.scaffold.system.mapper;

import com.anthonyzero.scaffold.system.entity.User;
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
 * @since 2019-07-07
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过账号获取用户信息
     * @param username
     * @return
     */
    User findByName(String username);


    /**
     * 分页查询用户
     * @param page  传递参数MB Page 即自动分页,必须放在第一位
     * @param user  搜索参数
     * @return
     */
    IPage<User> selectPageUser(Page page, @Param("user") User user);
}
