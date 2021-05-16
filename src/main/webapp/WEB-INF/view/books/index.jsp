<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 13.05.2021
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>books</title>
</head>
<body>

<table>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>isbn</th>
        <th>date</th>
        <th>author</th>
        <th>details</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:when test="${book != null}">
        <c:forEach var="item" items="${books}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.isbn}</td>
                <td>${item.date}</td>
                <td>${item.author}</td>
                <td><a href="books/details?id=${item.id}">book details</a> </td>
                <td><a href="books/edit?id=${item.id}">edit book</a> </td>
                <td><a href="books/delete?id=${item.id}">delete book</a> </td>
            </tr>
        </c:forEach>
        <c:otherwise>
            <tr>
                <td colspan="5">no data</td>
            </tr>
        </c:otherwise>
    </c:when>
</table>
</body>
</html>
