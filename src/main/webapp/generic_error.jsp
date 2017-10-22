<%--
  Created by IntelliJ IDEA.
  User: xym
  Date: 2017/10/22
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>generic_error</title>
</head>
<body>
<c:if test="${not empty errMsg}">
    <h2>${errMsg}</h2>
</c:if>

${errMsg}
</body>
</html>
