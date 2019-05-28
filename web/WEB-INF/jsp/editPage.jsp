<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sekten
  Date: 25.05.2019
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${empty part.name}">
    <title>Add</title>
    </c:if>
    <c:if test="${!empty part.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty part.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty part.name}">
    <c:url value="/edit" var="var"/>
</c:if>
<form:form  action="${var}" modelAttribute="part" method="post">
    <form:hidden  path ="id" value = "${part.id}"/>
    Name <form:input path="name" required ="true "/>
    <br><br>
    <!--label for = "isNecessary">Necessary</label-->
    Necessary
    <form:select path="isNecessary">
    <form:option value="true" label="Necessary"/>
    <form:option value="${1==0}" label="not Necessary"/>
    </form:select>
    <br><br>
    Quantity<form:input type="number" path="quantity" min="0" required="true" />
    <br><br>
    <button>Save</button>
</form:form>
<c:url value="/example" var="home"/>
<a href="${home}">Home</a>
</body>
</html>
