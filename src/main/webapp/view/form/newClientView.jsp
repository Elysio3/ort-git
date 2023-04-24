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
    <title>New Client</title>
</head>
<body>
<jsp:include page="/view/menuView.jsp"/>

<form method="post">
    <label for="input_society">Society :</label>
    <input id="input_society" type="text" name="society"/>
    <br>
    <label for="input_name">Name :</label>
    <input id="input_name" type="text" name="name"/>
    <br>
    <label for="input_surname">Surname :</label>
    <input id="input_surname" type="text" name="surname"/>
    <br>
    <label for="input_phone">Phone :</label>
    <input id="input_phone" type="tel" name="phone"/>
    <br>
    <label for="input_address">Address :</label>
    <input id="input_address" type="text" name="address"/>
    <br>

    <input type="submit" value="Validate"/>
</form>

</body>
</html>
