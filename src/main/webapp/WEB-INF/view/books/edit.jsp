<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 13.05.2021
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit</title>
</head>
<body>
<form method="post">
    <input type="text" name="id" placeholder="${book.id}"><hr/>
    <input type="text" name="name" placeholder="${book.name}"><hr/>
    <input type="text" name="isbn" placeholder="${book.isbn}"><hr/>
    <input type="text" name="author" placeholder="${book.author}"><hr/>
    <input type="submit" value="submit"><hr/>
    <input type="reset" value="reset">
</form>
</body>
</html>
