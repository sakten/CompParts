<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sekten
  Date: 26.05.2019
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<form:form action="${search}" method="get">
    <input type="text" name="searchTerm" id="searchTerm">
    <input type="submit" name="submit"  value="search">
</form:form>
<c:url value="/example" var="home"/>
<a href="${home}">Home</a>
</body>
</html>
