<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<div>
    <h1>User Page</h1>
    <a href="<c:url value="/"/>">Home page</a>
</div>
<div>
    <h3>Your information</h3>
    <table>
        <thead>
        <tr>
            <th>
                Role
            </th>
            <th>
                Email
            </th>
            <th>
                Password
            </th>
        </tr>
        </thead>
        <tr>
            <td>
                <c:out value="${sessionScope.role}"/>
            </td>
            <td>
                <c:out value="${sessionScope.email}"/>
            </td>
            <td>
                <c:out value="${sessionScope.password}"/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
