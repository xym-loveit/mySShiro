package com.xym.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * desc
 *
 * @author xym
 */
@Service
public class ShiroService {

    /*shiro的权限注解，对于value数组，其对应的元素为或(or)的关系*/
    @RequiresRoles(value = {"admin", "123"}, logical = Logical.OR)
    public void testMethod() {
        //shiro的session管理，可以将httpsession值透明传送到service中
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println("testMethod,date=" + new Date());
        System.out.println("session value=" + session.getAttribute("key1"));
    }
}