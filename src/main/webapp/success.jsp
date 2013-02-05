<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Succes</title>
    </head>
    <body>
    <h3>User <c:out value="${form.name}"/> Has been saved</h3>

    <form action="/employeeform" method="GET">
        <p>
            List from:<br>
            <input type="radio" name="providertype" value="XML" checked="checked"> XML File<br>
            <input type="radio" name="providertype" value="DB"> Data Base<br>
        </p>
        <input type="submit" value="View list"><br>
    </form>
    </body>
</html>