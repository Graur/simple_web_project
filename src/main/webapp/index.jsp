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
    <title>Welcome</title>
</head>
<body>
<p>Welcome! Please, sign up.</p>

<form action="LoginServlet" method="POST">
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="password"/>
    <input type="submit" value="Sign up">
</form>
</body>
</html>
