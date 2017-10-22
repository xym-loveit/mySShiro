package com.xym.shiro.realm;

import com.xym.shiro.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * 带盐值得加密
 *认证realm
 * @author xym
 */
public class EncryptSHA1Realm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.err.println("EncryptSHA1Realm doGetAuthenticationInfo");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //通过username读取数据库用户信息

        User user = new User();
        user.setUsername(username);

        if (user.getUsername().equals("unknow")) {
            throw new UnknownAccountException("该用户不存在！");
        }

        if (user.getUsername().equals("admin")) {
            user.setPassword("ce2f6417c7e1d32c1d81a797ee0b499f87c5de06");
        } else if (user.getUsername().equals("user")) {
            user.setPassword("073d4c3ae812935f23cb3f2a71943f49e082a718");
        }

        if (user.getUsername().equals("monster")) {
            throw new LockedAccountException("该用户被锁定！");
        }
        //盐值必须唯一，一般使用userid 在此使用username
        ByteSource salt = ByteSource.Util.bytes(username);

        return new SimpleAuthenticationInfo(user.getUsername() + "-->sha1", user.getPassword(), salt, getName());
    }

    public static void main(String[] args) {

        String algorithmName = "SHA1";
        Object source = "123456";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println("simpleHash=" + simpleHash);
    }
}