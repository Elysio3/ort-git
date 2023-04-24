<%--
  Created by IntelliJ IDEA.
  User: maelk
  Date: 08/12/2022
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Product</title>
</head>
<body>
<jsp:include page="/view/menuView.jsp"/>

<form method="post">
    <label for="input_name">Name :</label>
    <input id="input_name" type="text" name="name"/>
    <br>
    <label for="input_quantity">Quantity :</label>
    <input id="input_quantity" type="number" name="quantity"/>
    <br>

    <input type="submit" value="Validate"/>
</form>
</body>
</html>
