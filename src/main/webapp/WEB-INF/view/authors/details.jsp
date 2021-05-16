<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 13.05.2021
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>author details</title>
</head>
<body>
<h4>author details</h4>
<p>id ${author.id}</p><hr/>
<p>name ${author.details} </p><hr/>
<p>country ${author.country}</p><hr/>
<h4>Books list </h4>
<table>
    <thead>
        <th>#</th>
        <th>name</th>
        <th>isbn</th>
        <th>year</th>
    </thead>
   <tbody>
      <c:forEach var="book" items="${author.books}">
          <tr>
              <td><c:out value="${book.id}"/></td>
              <td><c:out value="${book.name}"/></td>
              <td><c:out value="${book.isbn}"/></td>
              <td><c:out value="${book.year}"/></td>
          </tr>
      </c:forEach>
   </tbody>

</table>
<a href="authors/">back to list</a>
</body>
</html>
