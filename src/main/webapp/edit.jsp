<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit page</title>
</head>
<body>
<div>
    <h1>Edit page</h1>
    <br>
    <a href="<c:url value="/"/>">Home page</a>
</div>
<div>
    <h3>List of Users</h3>
    <table style="border: black 1px solid">
        <thead>
        <tr>
            <th>
                Email
            </th>
            <th>
                Password
            </th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <td>
                    <c:out value="${user.password}"/>
                </td>
                <td>
                    <form action="<c:url value="/edit/delete"/>" method="post">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                        <button type="submit">Delete</button>
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/edit/update"/>" method="get">
                        <input type="hidden" name="updateid" value="<c:out value="${user.id}"/>">
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <h2>
        User Creation
    </h2>
    <form method="post" action="<c:url value="/edit/create"/>">
        <input type="text" name="email" placeholder="User's email">
        <input type="text" name="password" placeholder="User's password">
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
