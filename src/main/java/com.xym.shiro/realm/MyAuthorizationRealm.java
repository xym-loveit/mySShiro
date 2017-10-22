package com.xym.shiro.realm;

import com.xym.shiro.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;

/**
 * desc
 *
 * @author xym
 */
public class MyAuthorizationRealm extends AuthorizingRealm {

    /*授权方法*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("doGetAuthorizationInfo...");
        Object principal = principals.getPrimaryPrincipal();//获取配置的第一个realm的principal认证实体
        User user = (User) principal;//第一个realm的principal认证实体为user对象，所以才能强制转换
        //真实项目中需要查询数据库当前用户的角色集合
        HashSet roles = new HashSet();
        roles.add("user");//只要通过验证都有user角色
        if (user.getUsername().equals("admin")) {
            roles.add("admin");//如果是admin赋予admin角色
        }
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
        return authorizationInfo;
    }

    /*认证方法*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.err.println("EncryptSaltRealm doGetAuthenticationInfo");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        //通过username读取数据库用户信息

        User user = new User();
        user.setUsername(username);

        if (user.getUsername().equals("unknow")) {
            throw new UnknownAccountException("该用户不存在！");
        }

        if (user.getUsername().equals("admin")) {
            user.setPassword("038bdaf98f2037b31f1e75b5b4c9b26e");
        } else if (user.getUsername().equals("user")) {
            user.setPassword("098d2c478e9c11555ce2823231e02ec1");
        }

        if (user.getUsername().equals("monster")) {
            throw new LockedAccountException("该用户被锁定！");
        }
        //盐值必须唯一，一般使用userid 在此使用username
        ByteSource salt = ByteSource.Util.bytes(username);

        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }

    public static void main(String[] args) {

        String algorithmName = "MD5";
        Object source = "123456";
        Object salt = ByteSource.Util.bytes("user");
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println("simpleHash=" + simpleHash);
    }
}