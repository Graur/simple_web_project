<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 14.01.2018
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Error</h1>
    <h2><%=exception.getMessage() %><br/> </h2>
</div>
</body>
</html>