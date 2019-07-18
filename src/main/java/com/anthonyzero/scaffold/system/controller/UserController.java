package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.annotation.SysLog;
import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.GlobalException;
import com.anthonyzero.scaffold.common.utils.MD5Util;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 修改用户系统主题
     * @param theme
     * @return
     */
    @PostMapping("theme/update")
    public Response updateTheme(String theme) {
        User user = new User();
        user.setTheme(theme);
        user.setModifyTime(LocalDateTime.now());
        userService.update(user, new LambdaUpdateWrapper<User>().eq(User::getUserId, getUserId()));
        return Response.success();
    }

    /**
     * 修改用户头像
     * @param image
     * @return
     */
    @GetMapping("avatar/{image}")
    public Response updateAvatar(@PathVariable String image) {
        User user = new User();
        user.setAvatar(image);
        user.setModifyTime(LocalDateTime.now());
        userService.update(user, new LambdaUpdateWrapper<User>().eq(User::getUserId, getUserId()));
        return Response.success();
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("profile/update")
    public Response updateProfileInfo(User user) {
        user.setModifyTime(LocalDateTime.now());
        userService.update(user, new LambdaUpdateWrapper<User>().eq(User::getUserId, getUserId()));
        return Response.success();
    }

    /**
     * 修改用户密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("password/update")
    public Response updatePassword(String oldPassword, String newPassword) {
        if (!StringUtils.equals(getCurrentUser().getPassword(), MD5Util.encrypt(oldPassword, getCurrentUser().getUsername()))) {
            throw new GlobalException(CodeMsgEnum.PASSWORD_ERROR);
        }
        User user = new User();
        user.setPassword(MD5Util.encrypt(newPassword, getCurrentUser().getUsername()));
        user.setModifyTime(LocalDateTime.now());
        userService.update(user, new LambdaUpdateWrapper<User>().eq(User::getUserId, getUserId()));
        return Response.success();
    }


    /**
     * 用户列表
     * @param user
     * @param request
     * @return
     */
    @GetMapping("list")
    @RequiresPermissions("user:view")
    public Response userList(User user, RequestQuery request) {
        Map<String, Object> dataTable = getDataTable(userService.pageUser(user, request));
        return Response.success(dataTable);
    }

    /**
     * 检查用户名是否不存在
     * @param username
     * @param userId
     * @return
     */
    @GetMapping("check/{username}")
    public boolean checkUserName(@PathVariable String username, String userId) {
        return this.userService.findByName(username) == null || StringUtils.isNotBlank(userId);
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @SysLog("修改用户信息")
    @PostMapping("update")
    @RequiresPermissions("user:update")
    public Response updateUser(User user) {
        if (user.getUserId() == null)
            throw new GlobalException(CodeMsgEnum.REQUEST_ILLEGAL);
        this.userService.updateUser(user);
        return Response.success();
    }


    /**
     * 重置用户密码
     * @param usernames
     * @return
     */
    @SysLog("重置用户密码")
    @PostMapping("password/reset/{usernames}")
    @RequiresPermissions("user:password:reset")
    public Response resetPassword(@PathVariable String usernames) {
        String[] usernameArr = usernames.split(StringPool.COMMA);
        this.userService.resetPassword(usernameArr);
        return Response.success();
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @SysLog("新增用户")
    @PostMapping("add")
    @RequiresPermissions("user:add")
    public Response addUser(User user) {
        userService.createUser(user);
        return Response.success();
    }
}
