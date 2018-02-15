<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 11.02.2018
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<p>L2.1 Graur example</p>

<form action="ListUsersServlet" method="POST">
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="password"/>
    <input type="submit" value="Sign in">
</form>
</body>
</html>