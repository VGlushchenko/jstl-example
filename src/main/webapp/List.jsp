<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="style.css" media="all">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Success!</title>
    </head>
    <body>

        <h4>Employee list</h4>

		<table cellpadding="4" cellspacing="4">
            <tr><th>ID</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Birth Date</th>
                <th>Action</th>
            </tr>
            
			<c:if test="${employers != null }">
                <c:forEach var="employee" items="${employers}">
                    <tr>
                        <td> <c:out value="${employee.id}"/></td>
                        <td> <c:out value="${employee.name}"/></td>
                        <td> <c:out value="${employee.lastName}"/></td>
                        <td> <c:out value="${employee.email}"/></td>
                        <td> <c:out value="${employee.password}"/></td>
                        <td> <c:out value="${employee.birthDate}"/></td>
                        <td><input value="Edit" onClick="location.href='/update?action=edit&id=${employee.id}&name=${employee.name}&lastName=${employee.lastName}&email=${employee.email}&birthDate=${employee.birthDate}'" type="button"/>
                            <input value="Delete" onclick="location.href='/update?id=${employee.id}&action=delete'" type="button"/>
                        </td>
                    </tr>
                </c:forEach>
			</c:if>
        
        </table>
        
        <form action="/update" method="POST">
            <input type="submit" value="Add new User"><br>
        </form>
        
    </body>
</html>