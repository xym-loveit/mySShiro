<%--
  Created by IntelliJ IDEA.
  User: xym
  Date: 2017/10/22
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>List jsp</title>
</head>
<body>

<%--使用到了shiro权限的标签--%>

<h4>List Page</h4>

<br><br>
Welcome:<shiro:principal property="username"/>
<br><br>

<shiro:hasAnyRoles name="admin,user">
    <a href="/logout.jsp">logout</a>
</shiro:hasAnyRoles>

<shiro:hasRole name="admin">
    <br><br>
    <a href="admin.jsp">Admin page</a>
</shiro:hasRole>

<shiro:hasRole name="user">
    <br><br>
    <a href="user.jsp">User page</a>
</shiro:hasRole>

<br><br>
<a href="/shiro/testShiroAnnotation">Test ShiroAnnotation</a>

</body>
</html>
