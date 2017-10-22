package com.xym.shiro.realm;

import com.xym.shiro.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * 认证realm
 *
 * @author xym
 */
public class EncryptRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //通过username读取数据库用户信息

        User user = new User();
        user.setUsername(username);
        user.setPassword("fc1709d0a95a6be30bc5926fdb7f22f4");

        if (user.getUsername().equals("unknow")) {
            throw new UnknownAccountException("该用户不存在！");
        }

        if (user.getUsername().equals("admin")) {

        }

        if (user.getUsername().equals("monster")) {
            throw new LockedAccountException("该用户被锁定！");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    public static void main(String[] args) {

        String algorithmName = "MD5";
        Object source = "123456";
        Object salt = null;
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println("simpleHash=" + simpleHash);
    }
}