<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<h2>Say hello to this crud!</h2>

<a href="<c:url value="/user"/>">User Menu</a>
<a href="<c:url value="/admin"/>">Admin Menu</a>

<c:if test="${(sessionScope.role ne 'admin') and (sessionScope.role ne 'user')}">
    <form action="<c:url value="/login"/>" method="post">
        <input type="text" name="email" placeholder="Your email">
        <input type="password" name="password" placeholder="Password">
        <button type="submit">Submit</button>
    </form>
</c:if>

<c:if test="${(sessionScope.role eq 'admin') or (sessionScope.role eq 'user')}">
    You are <c:out value="${sessionScope.role}"/>
    <br>
    <a href="<c:url value="/logout"/>">Logout</a>
</c:if>
</body>
</html>
