package com.anthonyzero.scaffold.system.service.impl;

import com.anthonyzero.scaffold.common.authentication.ShiroRealm;
import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.utils.MD5Util;
import com.anthonyzero.scaffold.common.utils.SortUtil;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.entity.UserRole;
import com.anthonyzero.scaffold.system.mapper.UserMapper;
import com.anthonyzero.scaffold.system.service.UserRoleService;
import com.anthonyzero.scaffold.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ShiroRealm shiroRealm;

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

    @Override
    public void updateUser(User user) {
        user.setUsername(null); //为空的不修改
        user.setModifyTime(LocalDateTime.now());
        updateById(user);
        // 更新关联角色
        this.userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
        String[] roles = user.getRoleId().split(StringPool.COMMA); //逗号分隔
        setUserRoles(user, roles);

        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.equalsIgnoreCase(currentUser.getUsername(), user.getUsername())) {
            shiroRealm.clearCache();
        }

    }

    @Override
    public void resetPassword(String[] usernames) {
        Arrays.stream(usernames).forEach(username -> {
            User user = new User();
            user.setPassword(MD5Util.encrypt(User.DEFAULT_PASSWORD, username));
            this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        });
    }

    /**
     * 保存用户角色信息
     * @param user
     * @param roles
     */
    private void setUserRoles(User user, String[] roles) {
        List<UserRole> userRoles = new ArrayList<>();
        Arrays.stream(roles).forEach(roleId -> {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(Integer.valueOf(roleId));
            userRoles.add(ur);
        });
        userRoleService.saveBatch(userRoles);
    }
}
