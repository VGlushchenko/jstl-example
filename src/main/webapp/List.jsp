<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Succes!</title>
    </head>
    <body>

        <h4>User <%= request.getAttribute("firstName")%></h4>

        <table border="1" cellpadding="4" cellspacing="4" frame="3">
            <tr>
                <td>First Name</td>
                <td>Second Name</td>
                <td>Email</td>
                <td>Password</td>
                <td>Birth Date</td>
                <td>Action</td>
            </tr>
            <tr>
                <td>
                    <%= request.getAttribute("firstName")%>
                </td>

                <td>
                    <%= request.getAttribute("lastName")%>
                </td>

                <td>
                    <%= request.getAttribute("email")%>
                </td>

                <td>
                    <%= request.getAttribute("password")%>
                </td>

                <td>
                    <%= request.getAttribute("birth_date")%>
                </td>
                <td>
                    <a href="/edit?firstName=<%=request.getAttribute("firstName")%>&lastName=<%=request.getAttribute("lastName")%>&email=<%=request.getAttribute("email")%>&birth_date=<%=request.getAttribute("birth_date")%>">Edit</a>
                </td>
            </tr>
        </table>
        <form action="/validation" method="POST">
            <input type="submit" value="Add new User"><br>
        </form>
    </body>
</html>