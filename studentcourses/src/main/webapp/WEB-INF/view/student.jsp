<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${student.name}</title>
</head>
<body>
<div align="left">
    <h1>${student.name}</h1>
    Courses: ${student.courses}
    <br>
    <br>
    <a href="/students/list">Back</a>
</div>
</body>
</html>

