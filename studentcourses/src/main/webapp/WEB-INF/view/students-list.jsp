<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Students List</title>
</head>
<body>
<div align="center">
    <h1>Students List</h1>
    <table border="1">
        <th>Id</th>
        <th>Name</th>

        <c:forEach var="student" items="${studentsList}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>

            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="/">Back To Main Page</a>
</div>
</body>
</html>
