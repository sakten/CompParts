<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sekten
  Date: 28.05.2019
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fail</title>
</head>
<body>
<c:if test="${param[\"reason\"].equals(\"notFound\")}">
<c:url value="/add" var="add"/>
<c:url value="/search" var="search"/>
    Part with this name not found. You can <a href="${add}">create</a> it or go back to <a href="${search}" >search</a>.
</c:if>
<c:if test="${param[\"reason\"].equals(\"alreadyExists\")}">
    <c:url value="/add" var="add"/>
    Part with this name already exists You can go back to <a href="${add}" >creation</a>
</c:if>

<br>
<c:url value="/example" var="home"/>
<a href="${home}">Home</a>
</body>
</html>
