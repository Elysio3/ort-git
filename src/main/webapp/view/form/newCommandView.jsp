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
    <title>New Command</title>
</head>
<body>
<jsp:include page="/view/menuView.jsp"/>
<form method="post">
    <label for="input_code_client">code_client :</label>
    <select name="code_client" id="input_code_client">
        <option value="">--Please choose an option--</option>
        <c:forEach items="${ clients }" var="client" varStatus="boucle">
            <option value="${ client.code }">${ client.name } ${ client.surname }</option>
        </c:forEach>

    </select>
    <br>

    <input type="submit" value="Validate"/>
</form>

</body>
</html>
