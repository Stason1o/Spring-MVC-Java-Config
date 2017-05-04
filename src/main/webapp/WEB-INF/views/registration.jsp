<%--
  Created by IntelliJ IDEA.
  User: sbogdanschi
  Date: 3/05/2017
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<c:url var="addAction" value="/registration"/>
<form:form action="${addAction}" commandName="user">

    <form:label path="firstName">Enter your First Name</form:label>
    <form:input id="firstName" name="firstName" path="firstName" />
    <form:errors path="firstName"/>
    <br>

    <form:label path="lastName">Enter your Last Name</form:label>
    <form:input id="lastName" name="lastName" path="lastName" />
    <form:errors path="lastName"/>
    <br>

    <form:label path="username">Enter your username</form:label>
    <form:input id="username" name="username" path="username" />
    <form:errors path="username"/>
    <br>

    <form:label path="password">Please enter your password</form:label>
    <form:password id="password" name="password" path="password" />
    <form:errors path="password"/>
    <br>

    <form:label path="confirmPassword">Please confirm your password</form:label>
    <form:password id="confirmPassword" name="confirmPassword" path="confirmPassword" />
    <form:errors path="confirmPassword"/>
    <br>

    <form:label path="email">Please enter your email</form:label>
    <form:input type="email" id="email" name="email" path="email" />
    <form:errors path="email"/>
    <br>

    <form:input type="hidden" id="enabled" value="1" name="enabled" path="enabled"/>
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
