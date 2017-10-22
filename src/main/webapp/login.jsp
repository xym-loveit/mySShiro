<%--
  Created by IntelliJ IDEA.
  User: xym
  Date: 2017/10/22
  Time: 1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h4>Login Page</h4>
<form action="/shiro/login" method="post">
    <br><br>
    username:<input type="text" name="username"/>
    <br><br>
    password:<input type="password" name="password"/>
    <br><br>
    <input type="submit" value="login">
</form>
</body>
</html>
