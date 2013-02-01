<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Succes</title>
</head>
<body>
<h3>User <%=request.getParameter("firstName")%> Has been saved</h3>

<form action="/validation" method="GET">
    <input type="submit" value="View list"><br>
</form>

</body>
</html>