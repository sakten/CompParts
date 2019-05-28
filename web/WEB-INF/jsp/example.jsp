<%--
  Created by IntelliJ IDEA.
  User: sekten
  Date: 22.05.2019
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>EXAMPLE</title>
</head>
<body>

<h2>Parts</h2>
<c:url value="/search" var="search"/>
<a href="${search}">Search</a>
<table >
    <tr>
        <td width="200" align="right">Filter:</td>
        <td>
        <c:url value="/example" var="urlUnn">
            <c:param name="showType" value="2"/>
        </c:url>
        <a href="${urlUnn}">Necessary parts</a>
        </td>
        <td>
        <c:url value="/example" var="urlNess">
            <c:param name="showType" value="1"/>
        </c:url>
        <a href="${urlNess}">Unnecessary parts</a>
        </td>
        <td>
            <c:url value="/example" var="urlNess">
                <c:param name="showType" value="0"/>
            </c:url>
            <a href="${urlNess}">All parts</a>
        </td>
    </tr>
    <tr>
        <th>name</th>
        <th>isNecessary</th>
        <th>quantity</th>
        <th>           </th>
        <th>
        </th>
    </tr>
    <c:forEach var="part" items="${partsList}">
        <tr>
            <td>${part.name}</td>
            <td>${part.isNecessary}</td>
            <td>${part.quantity}</td>
            <td>
                <a href="/edit/${part.id}">edit</a>
                <a href="/delete/${part.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
    <tr><td>
        <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
            <c:url value="/example" var="url">
            <c:param name="currPage" value="${i.index}"/>
            <c:param name="showType" value="${showType}"/>
            </c:url>
            <a href="${url}">${i.index}</a>
        </c:forEach>
    </td>
    </tr>
    <tr>
    </tr>
</table>
Now you can assemble ${wholeComputers} computers

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new part</a>
</body>
</html>
