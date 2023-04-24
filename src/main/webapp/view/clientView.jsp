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
    <title>Clients - list</title>
</head>
<body>
<jsp:include page="/view/menuView.jsp"/>
Clients :
<br>

<table style="border: black solid 1px">
    <tr>
        <th>code</th>
        <th>society</th>
        <th>name</th>
        <th>surname</th>
        <th>phone</th>
        <th>address</th>
    </tr>

    <c:forEach items="${ clients }" var="client" varStatus="boucle">
        <tr>
            <td>${ client.code }</td>
            <td>${ client.society }</td>
            <td>${ client.name }</td>
            <td>${ client.surname }</td>
            <td>${ client.phone }</td>
            <td>${ client.address }</td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
