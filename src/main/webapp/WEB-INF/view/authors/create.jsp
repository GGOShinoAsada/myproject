<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 13.05.2021
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create new model</title>
</head>
<body>
<form method="post">
    <input type="text" placeholder="${item.id}"><hr/>
    <input type="text" placeholder="${item.name}"><hr/>
    <input type="text" placeholder="${item.description}"/><hr/>
    <input type="text" placeholder="${item.country}"/><hr/>
    <a href="authors/index">authors list</a><hr/>
    <input type="submit" value="submit form"><hr/>
</form>
</body>
</html>
