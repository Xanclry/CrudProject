<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<h1>Update user</h1>
<div>
    <form action="<c:url value="/admin/update"/>" method="post">
        <input type="hidden" name="updateid" value="<c:out value="${param.updateid}"/>">
        <input type="text" name="email" placeholder="User's email">
        <input type="text" name="password" placeholder="User's password">
        <input type="text" name="role" placeholder="User's role">
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
