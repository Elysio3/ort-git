<%--
  Created by IntelliJ IDEA.
  User: maelk
  Date: 08/12/2022
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product - list</title>
</head>
<body>
<jsp:include page="/view/menuView.jsp"/>
Product :
<br>

<table style="border: black solid 1px">
    <tr>
        <th>code</th>
        <th>name</th>
        <th>quantity</th>
    </tr>

    <c:forEach items="${ products }" var="product" varStatus="boucle">
        <tr>
            <td>${ product.code }</td>
            <td>${ product.name }</td>
            <td>${ product.quantity }</td>
        </tr>
    </c:forEach>

</table>
