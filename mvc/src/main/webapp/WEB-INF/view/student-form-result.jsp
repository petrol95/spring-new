<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>

<html>
    <body>
        <h1>Student's Form Results</h1>
        <br>
        First name: ${student.firstName}
        <br>
        Last name: ${student.lastName}
        <br>
        Country: ${student.country}
        <br>
        Programming languages:
        <br>
        <ul>
            <c:forEach var="item" items="${student.programmingLanguages}">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </body>
</html>
