<%--
  Created by IntelliJ IDEA.
  User: maelk
  Date: 08/12/2022
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Command - list</title>
</head>
<body>
<jsp:include page="/view/menuView.jsp"/>
Command :
<br>

Afficher les commandes d'un client :
<form method="post" action="Index">
    <input type="hidden" name="page" value="get-command-by-client">
    <label for="input_client">client :</label>
    <select name="code_client" id="input_client">
        <option value="">--Please choose an option--</option>
        <c:forEach items="${ clients }" var="client" varStatus="boucle">
            <option value="${ client.code }">${ client.name } ${ client.surname }</option>
        </c:forEach>

    </select>
    <br>

    <input type="submit" value="Validate"/>
</form>

<table style="border: black solid 1px">
    <tr>
        <th>code</th>
        <th>code_client</th>
    </tr>

    <c:forEach items="${ commands }" var="command" varStatus="boucle">
        <tr>
            <td>${ command.code }</td>
            <td>
                ${ command.code_client }
                <c:forEach items="${ clients }" var="client" varStatus="boucle">
                    <c:if test="${client.code == command.code_client}">
                        ${ client.name } ${ client.surname }
                    </c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>

</table>