## 通过Shiro实现安全控制 
 
1、外部程序通过Subject与shiro进行交互。 
 
2、Subject通过SecurityManager进行逻辑处理，SecurityManager管理着shiro各个对象
  
3、通过extends AuthenticatingRealm实现用户认证  

4、通过extends AuthorizingRealm实现用户授权及认证，实现doGetAuthorizationInfo(授权)、doGetAuthenticationInfo(认证)2个方法

5、通过SimpleAuthorizationInfo返回授权信息，如角色（roles），通过SimpleAuthenticationInfo返回认证实体，如：username、user等  

6、ModularRealmAuthenticator是shiro的多realm认证器，AuthenticationStrategy来指定多realm认证策略，使用其实现类AllSuccessfulStrategy、AtLeastOneSuccessfulStrategy、FirstSuccessfulStrategy

7、通过配置realm的credentialsMatcher的HashedCredentialsMatcher类型对象
实现认证信息加密
```
  <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
                <property name="hashAlgorithmName" value="SHA1"></property>
                <property name="hashIterations" value="1024"></property>
            </bean>
```

8、配置ShiroFilterFactoryBean的属性使用shiro的访问控制
```
<property name="securityManager" ref="securityManager"/>
<property name="loginUrl" value="/login.jsp"/>
<property name="successUrl" value="/list.jsp"/>
<property name="unauthorizedUrl" value="/unauthorized.jsp"/>
<property name="filterChainDefinitions">
    <value>
        /shiro/login=anon
        /login.jsp=anon
        /logout.jsp=logout
        /user.jsp=roles[user]
        /admin.jsp=roles[admin]
        # everything else requires authentication:
        /** = authc
    </value>
</property>
```

shiro默认支持多种类型的过滤器：参见DefaultFilter
常见： 
 
anon--AnonymousFilter  
authc--FormAuthenticationFilter  
logout--LogoutFilter  
user--UserFilter  

9、还可通过注解使用权限访问控制  
```
/*shiro的权限注解，对于value数组，其对应的元素为或(or)的关系*/
@RequiresRoles(value = {"admin", "123"}, logical = Logical.OR)

```
10、通过shiro的session会话管理支持，可以将httpsession由Controller层透明传递至service层  

11、通过以下配置可以更改rememberMe中的cookie有效存活时间  

```
<!--cookie有效时间为10秒-->
<property name="rememberMeManager.cookie.maxAge" value="10"/>
```

12、通过shiro一些标签，可以基于权限控制页面功能显示与否。  
```
<br><br>
<!--获取认证实体中user对象的username属性-->
Welcome:<shiro:principal property="username"/>
<br><br>

<!--具有admin或user角色的授权才显示-->
<shiro:hasAnyRoles name="admin,user">
    <a href="/logout.jsp">logout</a>
</shiro:hasAnyRoles>

<!--具有admin角色的授权才显示-->
<shiro:hasRole name="admin">
    <br><br>
    <a href="admin.jsp">Admin page</a>
</shiro:hasRole>
```

13、通过securityManager的cacheManager实现缓存,依赖Ehcache jar  

Quickstart是学习shiro很好的一个例子