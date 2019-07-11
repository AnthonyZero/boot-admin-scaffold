package com.anthonyzero.scaffold.system.controller;

import com.anthonyzero.scaffold.common.core.BaseController;
import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
}
