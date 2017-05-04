<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sbogdanschi
  Date: 28/04/2017
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin </title>
</head>
<body>
<p>admin page; ${username}</p>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
