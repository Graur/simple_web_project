<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 14.01.2018
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Users Management</h1>
    <h2>
        <a href="/insert">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/">List All Users</a>
    </h2>
</div>
<div align="center">
    <c:if test="${user != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Update User
                        </c:if>
                        <c:if test="${user == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>
                <tr>
                    <th>ID: </th>
                    <td>
                        <c:out value="${user.id}" />
                    </td>
                </tr>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" size="45" placeholder="Enter name"
                               value="<c:out value='${user.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Login: </th>
                    <td>
                        <input type="text" name="login" size="45" placeholder="Enter login"
                               value="<c:out value='${user.login}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input type="text" name="password" size="45" placeholder="Enter password"
                               value="<c:out value='${user.password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
