<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 13.05.2021
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create</title>
</head>
<body>
<form method="post">
    <input type="text" placeholder="${book.id}" readonly><hr/>
    <input type="text" placeholder="${book.name}"><hr/>
    <input type="text" placeholder="${book.isbn}"><hr/>
    <input type="text" placeholder="${book.author.name}"/><hr/>
    <input type="text" placeholder="${book.rating}"><hr/>
    <input type="text" value="send"><hr/>
    <input type="reset" value="reset">
</form>
</body>
</html>
