package com.xym.shiro.realm;

import com.xym.shiro.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * 认证realm
 *
 * @author xym
 */
public class ShiroRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.err.println("ShiroRealm doGetAuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //通过username读取数据库用户信息

        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");

        if (user.getUsername().equals("unknow")) {
            throw new UnknownAccountException("该用户不存在！");
        }

        if (user.getUsername().equals("monster")) {
            throw new LockedAccountException("该用户被锁定！");
        }

        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }

}