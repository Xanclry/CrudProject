<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="<c:url value="/user"/>">User Menu</a>
<a href="<c:url value="/admin"/>">Admin Menu</a>
<form action="<c:url value="/login"/>" method="post">
    <input type="text" name="email" placeholder="Your email">
    <input type="password" name="password" placeholder="Password">
    <button type="submit">Submit</button>
</form>
<c:if test="${(sessionScope.role eq 'admin') or (sessionScope.role eq 'user')}">
    You are <c:out value="${sessionScope.role}"/>
    <a href="<c:url value="/logout"/>">Logout</a>
</c:if>
</body>
</html>
