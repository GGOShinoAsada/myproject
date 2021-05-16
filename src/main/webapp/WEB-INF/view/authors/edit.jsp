<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 13.05.2021
  Time: 21:58
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
    <input type="text" name="id" placeholder="${author.id}"><hr/>
    <input type="text" name="name" placeholder="${author.name}"><hr/>
    <input  type="text" name="age" placeholder="${author.age}"><hr/>
    <input type="text" name="country" placeholder="${item.country}"><hr/>
</form>
</body>
</html>
