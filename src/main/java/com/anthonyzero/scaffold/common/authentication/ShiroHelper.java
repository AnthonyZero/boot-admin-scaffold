package com.anthonyzero.scaffold.common.authentication;

import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.stereotype.Component;

/**
 * @author anthonyzero
 */
@Component
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentUserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }
}
