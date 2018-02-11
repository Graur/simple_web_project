<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 11.02.2018
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>Hello, <c:out value="${user.login}" /> </h1>
    </div>
</body>
</html>
