package com.xym.shiro.factory;

import java.util.LinkedHashMap;

/**
 * desc
 *
 * @author xym
 */
public class FilterChainDefinitionMapBuilder {

    /**
     * 模拟数据库构造访问权限
     *
     * @return
     */
    public LinkedHashMap buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("/shiro/login", "anon");
        linkedHashMap.put("/login.jsp", "anon");
        linkedHashMap.put("/logout.jsp", "logout");
        linkedHashMap.put("/user.jsp", "authc,roles[user]");//只能通过认证
        linkedHashMap.put("/admin.jsp", "authc,roles[admin]");//只能通过认证
        linkedHashMap.put("/list.jsp", "user");//通过记住我或认证访问
        linkedHashMap.put("/**", "authc");
        return linkedHashMap;
    }
}