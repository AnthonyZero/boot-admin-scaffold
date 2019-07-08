package com.anthonyzero.scaffold.common.authentication;

import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.GlobalException;
import com.anthonyzero.scaffold.system.entity.Permission;
import com.anthonyzero.scaffold.system.entity.Role;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.service.PermissionService;
import com.anthonyzero.scaffold.system.service.RoleService;
import com.anthonyzero.scaffold.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证和授权
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 授权模块，获取用户角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object object =  SecurityUtils.getSubject().getPrincipal();

        User user = (User) object;
        String userName = user.getUsername();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<Role> roleList = this.roleService.findUserRole(userName);
        Set<String> roleSet = roleList.stream().map(Role::getName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<Permission> permissionList = this.permissionService.findUserPermissions(userName);
        Set<String> permissionSet = permissionList.stream().map(Permission::getPerms).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        // 通过用户名到数据库查询用户信息
        User user = userService.findByName(userName);

        if (user == null)
            throw new GlobalException(CodeMsgEnum.INCORRECT_PASSWORD);
        if (!StringUtils.equals(password, user.getPassword()))
            throw new GlobalException(CodeMsgEnum.INCORRECT_PASSWORD);
        if (SysConstant.STATUS_LOCK.equals(user.getStatus()))
            throw new GlobalException(CodeMsgEnum.LOCKED_ACCOUNT);
        return new SimpleAuthenticationInfo(user, password, getName());
    }


    /**
     * 清除当前用户权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm,
     * 然后调用其 clearCache方法。
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
