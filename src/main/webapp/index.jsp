<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Login Form</h1>

<%
    if (!(null == request.getAttribute("errorLogs"))){
%>
<%=request.getAttribute("errorLogs") + "\n" %>
<% }%>

<form action="/validation" method="POST">
    First Name<br>
    <input type="text" name="firstName" value="<%=(null != request.getParameter("firstName")) ? request.getParameter("firstName") : "" %>"><br>
    Last Name<br>
    <input type="text" name="lastName" value="<%=(null != request.getParameter("lastName")) ? request.getParameter("lastName") : "" %>"><br>
    Email<br>
    <input type="text" name="email" value="<%=(null != request.getParameter("email")) ? request.getParameter("email") : "" %>"><br>
    Password<br>
    <input type="password" name="password" value=""><br>
    Confirm Password<br>
    <input type="password" name="confirmPassword" value=""><br>
    Birth Date<br>
    <input type="text" name="birth_date" value="<%=(null != request.getParameter("birth_date")) ? request.getParameter("birth_date") : "" %>"><br>
    <input type="submit" value="Add"><br>
</form>

<form action="/validation" method="GET">
    <input type="submit" value="View list"><br>
</form>

</body>
</html>